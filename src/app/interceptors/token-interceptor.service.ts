import { Injectable } from '@angular/core';
import {HTTP_INTERCEPTORS, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TokenService} from '../services/security/token.service';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor {

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let autReq = req;
        const token = this.tokenService.getToken();
        if (token != null) {
            autReq = req.clone({ headers: req.headers.set('Authorization', 'Bearer ' + token) });
        }
        return next.handle(autReq);
    }

    constructor(private tokenService: TokenService) { }
}
export const interceptorProvider = [{provide: HTTP_INTERCEPTORS, useClass: TokenInterceptorService, multi: true}];
