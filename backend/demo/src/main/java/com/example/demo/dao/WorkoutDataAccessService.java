package com.example.demo.dao;

import com.example.demo.model.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("postgres_workout")
public class WorkoutDataAccessService implements WorkoutDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WorkoutDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int insertWorkout(Workout workout) {
        final String sql="INSERT INTO workout (name, duration, complexity, user_id) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql,
                workout.getName(),
                workout.getDuration(),
                workout.getComplexity(),
                workout.getUser_id());
        return 0;
    }

    @Override
    public List<Workout> selectAllWorkouts() {
        final String sql="SELECT workout_id, name, complexity, duration, user_id FROM workout";
        List<Workout> workouts = jdbcTemplate.query(sql, (resultSet,i) -> {
            return new Workout(
                            Integer.parseInt(resultSet.getString("workout_id")),
                            resultSet.getString("name"),
                            resultSet.getString("complexity"),
                            Integer.parseInt(resultSet.getString("duration")),
                            Integer.parseInt(resultSet.getString("user_id"))
            );
        });
        return workouts;
    }

    @Override
    public List<Workout> selectWorkoutsByUserId(int user_id) {
        final String sql = "SELECT workout_id, name, complexity, duration, user_id FROM workout WHERE user_id = ?";
        List<Workout> workouts = jdbcTemplate.query(sql,
                    new Object[]{user_id},
                    (resultSet,i) -> {
                    return new Workout(
                        Integer.parseInt(resultSet.getString("workout_id")),
                        resultSet.getString("name"),
                        resultSet.getString("complexity"),
                        Integer.parseInt(resultSet.getString("duration")),
                        Integer.parseInt(resultSet.getString("user_id"))
                    );
        });
        return workouts;
    }

    @Override
    public Optional<Workout> selectWorkoutById(int workout_id) {
        final String sql = "SELECT workout_id, name, complexity, duration, user_id FROM workout WHERE workout_id = ?";

        Workout workout = jdbcTemplate.queryForObject(sql,
                new Object[]{workout_id},
                (resultSet,i) -> {
                    return new Workout(
                            Integer.parseInt(resultSet.getString("workout_id")),
                            resultSet.getString("name"),
                            resultSet.getString("complexity"),
                            Integer.parseInt(resultSet.getString("duration")),
                            Integer.parseInt(resultSet.getString("user_id"))
                    );
                });
        return Optional.ofNullable(workout);
    }


    @Override
    public int deleteWorkoutByWorkoutId(int workout_id) {
        final String sql = "DELETE FROM workout WHERE workout_id = ?";

        jdbcTemplate.update(sql, workout_id);
        return 0;
    }

    @Override
    public int deleteAllUserWorkouts(int user_id) {
        final String sql = "DELETE FROM workout WHERE user_id = ?";

        jdbcTemplate.update(sql, user_id);
        return 0;
    }

    @Override
    public int updateWorkoutById(int workout_id, Workout workout) {
        final String sql = "UPDATE workout SET name='"+workout.getName()+"'," +
                "'"+workout.getDuration()+"', complexity='"+workout.getComplexity()+"' " +
                "WHERE workout_id = ?";

        jdbcTemplate.update(sql, workout_id);
        return 0;
    }
}
