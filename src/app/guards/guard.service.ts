import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {TokenService} from '../services/security/token.service';


@Injectable({
    providedIn: 'root'
})
export class GuardService implements CanActivate {

    tieneAcceso = false;

    constructor(private tokenService: TokenService, private router: Router) {
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        const rolesAutorizados: string[] = route.data.rolesAutorizados;
        const rolesUsuarioLogeado: string[] = this.tokenService.getAuthorities();
        if (rolesAutorizados.indexOf('ANY') !== -1 && rolesUsuarioLogeado.length > 0) {
            return true;
        }
        this.tieneAcceso = false;
        /*
        Hay que buscar si alguno de los roles del usuario, contenidos en rolesUsuarioLogeado,
        existe en el arreglo de rolesAutorizados
        * */
        rolesUsuarioLogeado.forEach(rolUsuario => {
            rolesAutorizados.forEach(rolAutorizado => {
                if (rolUsuario === rolAutorizado) {
                    this.tieneAcceso = true;
                }
            });
        });
        if (!this.tokenService.getToken() || !this.tieneAcceso) {
            // this.router.navigate(['**']);
            if (rolesUsuarioLogeado.indexOf('ROLE_MIEMBRO_REGISTRADO') !== -1) {
                this.router.navigate(['']);
                return false;
            } else {
                this.router.navigate(['/login']);
                return false;
            }
        }
        return this.tieneAcceso;
    }

}
