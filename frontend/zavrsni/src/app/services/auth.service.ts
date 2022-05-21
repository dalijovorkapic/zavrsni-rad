import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { BehaviorSubject, Subject, take, tap } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  tokens: any;
  role: string = "";
  name: string = "";

  user = new BehaviorSubject<any>(null);

  constructor(private http: HttpClient, private router: Router) {
    if (localStorage.getItem('user')) {
      this.user = new BehaviorSubject<any>(JSON.parse(localStorage.getItem('user') || '{}'));
    }
   }


  login(email:string, password: string) {

    let body = new URLSearchParams();
    body.set('email',email);
    body.set('password', password);

    let options = {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
  };

    return this.http.post<any>(environment.baseUrl+"/api/login", body.toString(), options)
      .pipe(take(1), tap(res => {
        
        this.setSession(res)
      }));
      
  }

  refreshToken(token:string) {
    return this.http.post<any>(environment.baseUrl+"/api/token/refresh/", {refresh_token: token});
  }

  private setSession(token: any) {

    const helper = new JwtHelperService();
    const decodedToken = helper.decodeToken(token.access_token)

    this.role = decodedToken.role[0];

    let user: Object = {
      access_token: token.access_token,
      refresh_token: token.refresh_token,
      user_name: token.user_name,
      role: this.role,
      user_id: token.user_id
    }
    this.name = token.user_name;

    localStorage.setItem('user', JSON.stringify(user));
    this.user.next(token.user_name);
      
  
    
    const expirationDate = helper.getTokenExpirationDate(token.access_token);
    const isExpired = helper.isTokenExpired(token.access_token);
    
    this.router.navigateByUrl('/home');
  }

  logout() {
    this.user.next(null);
    localStorage.removeItem('user');
    this.router.navigateByUrl('/login');
  }

  isLoggedIn() {
    const user = JSON.parse(localStorage.getItem("user") || '{}');
    
    if (user == {}) {
      return false;
    }

    const token = user.access_token;

    const helper = new JwtHelperService();
    const isExpired = helper.isTokenExpired(token);

    return !isExpired;


  }
  isLoggedOut() {
    return !this.isLoggedIn();
  }

  getUserFromLocalStorage() {
    const user = JSON.parse(localStorage.getItem("user") || '{}');
    return {user_id: user.user_id, user_name: user.user_name, role: this.role}
  }
}