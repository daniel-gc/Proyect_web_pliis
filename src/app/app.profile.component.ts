import {AppMainComponent} from './app.main.component';
import {Component, OnInit} from '@angular/core';
import {trigger, state, transition, style, animate} from '@angular/animations';
import {TokenService} from './services/security/token.service';
import {Router} from '@angular/router';

@Component({
    selector: 'app-inline-profile',
    template: `
        <div class="profile" [ngClass]="{'profile-expanded':active}">
            <a href="#" (click)="onClick($event)">
<!--                <img class="profile-image" src="assets/layout/images/avatar.png" [src]=""/>-->
<!--                <img class="profile-image"  [src]= "imageAvatar" />-->
                <span class="profile-name">{{nombreUsuario.substring(0, nombreUsuario.indexOf("@"))}}</span>
                <i class="material-icons">keyboard_arrow_down</i>
            </a>
        </div>

        <ul class="ultima-menu profile-menu" [@menu]="active ? 'visible' : 'hidden'">
            <li role="menuitem">
                <a href="#/cambioContrasena" class="ripplelink" [attr.tabindex]="!active ? '-1' : null">
                    <i class="material-icons">person</i>
                    <span>Contraseña</span>
                </a>
            </li>
<!--            <li role="menuitem">-->
<!--                <a href="#" class="ripplelink" [attr.tabindex]="!active ? '-1' : null">-->
<!--                    <i class="material-icons">security</i>-->
<!--                    <span>Privacy</span>-->
<!--                </a>-->
<!--            </li>-->
            <li role="menuitem" *ngIf="tokenService.getAuthorities().indexOf('ROLE_AFILIADO') !== -1">
                <a href="#/beneficiarios" class="ripplelink" [attr.tabindex]="!active ? '-1' : null">
                    <i class="material-icons">settings_application</i>
                    <span>Beneficiarios</span>
                </a>
            </li>
            <li role="menuitem" *ngIf="(tokenService.getAuthorities().indexOf('ROLE_MIEMBRO_REGISTRADO') !== -1) || (tokenService.getAuthorities().indexOf('ROLE_MIEMBRO_ACTIVO') !== -1)">
                <a href="#/planesMembrecia" class="ripplelink" [attr.tabindex]="!active ? '-1' : null">
                    <i class="material-icons">credit_card</i>
                    <span>Planes de membrecía</span>
                </a>
            </li>
            <li role="menuitem" *ngIf="(tokenService.getAuthorities().indexOf('ROLE_MIEMBRO_REGISTRADO') !== -1) || (tokenService.getAuthorities().indexOf('ROLE_MIEMBRO_ACTIVO') !== -1)">
                <a href="#/historialPago" class="ripplelink" [attr.tabindex]="!active ? '-1' : null">
                    <i class="material-icons">history</i>
                    <span>Historial de pagos</span>
                </a>
            </li>
            <li role="menuitem">
                <a class="ripplelink" (click)="ejecutaLogout()" [attr.tabindex]="!active ? '-1' : null" >
                    <i class="material-icons">power_settings_new</i>
                    <span>Logout</span>
                </a>
            </li>
        </ul>
    `,
    animations: [
        trigger('menu', [
            state('hidden', style({
                height: '0px'
            })),
            state('visible', style({
                height: '*'
            })),
            transition('visible => hidden', animate('400ms cubic-bezier(0.86, 0, 0.07, 1)')),
            transition('hidden => visible', animate('400ms cubic-bezier(0.86, 0, 0.07, 1)'))
        ])
    ]
})
export class AppInlineProfileComponent implements OnInit {

    public href = '';
    active: boolean;
    usuarioLoged   = '';
    imageAvatar = '';
    nombreUsuario: string;

    constructor(public app: AppMainComponent, public tokenService: TokenService,
                private router: Router) {
        this.imageAvatar = 'assets/layout/images/' + this.usuarioLoged + '.png';
    }

    ngOnInit(): void {
        // this.href = '/logout' + this.router.url;
        this.nombreUsuario = this.tokenService.getUserName();
    }

    onInit() {
        this.imageAvatar = 'assets/layout/images/' + this.usuarioLoged + '.png';
    }

    onClick(event) {
        this.active = !this.active;
        setTimeout(() => {
          this.app.layoutMenuScrollerViewChild.moveBar();
        }, 450);
        event.preventDefault();
    }

    logOut() {
        this.imageAvatar = 'assets/layout/images/' + this.usuarioLoged + '.png';
    }

    ejecutaLogout() {
        const uri = '/logout/' + '\'' + this.router.url + '\'';
        this.router.navigate(['/logout', this.router.url]);
    }
}
