import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

export class BasicHttpInterceptor implements HttpInterceptor{

  constructor() {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // add authorization header with basic auth credentials if available
    let currentUser = JSON.parse(localStorage.getItem('tokenSystig'));
    if (currentUser && currentUser.authdata) {
        request = request.clone({
            setHeaders: {
                Authorization: `Basic ${currentUser.authdata}`
            }
        });
    }
    return next.handle(request);
  }
}
