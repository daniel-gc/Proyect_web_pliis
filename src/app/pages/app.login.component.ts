import {Component, forwardRef, OnInit} from '@angular/core';

import {Router} from '@angular/router';
import {LoginUsuario} from '../models/login-usuario';
import {AuthService} from '../services/security/auth.service';
import {TokenService} from '../services/security/token.service';
import {ConfirmationService, MenuItem, MessageService} from 'primeng/api';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {NG_VALUE_ACCESSOR} from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './app.login.component.html',
    providers: [MessageService,
        ConfirmationService,
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => AppLoginComponent),
            multi: true,
        }]
})
export class AppLoginComponent implements OnInit {
    form: any = {};
    usuario: LoginUsuario;
    isLogged = false;
    isLoginFail = false;
    roles: string[] = [];
    errorMsg = '';
    items: MenuItem[];
    @BlockUI() blockUI: NgBlockUI;

    constructor(private authService: AuthService, private tokenService: TokenService, private router: Router,
                private messageService: MessageService,
                private confirmationService: ConfirmationService) { }

    ngOnInit() {
        if (this.tokenService.getToken()) {
            this.isLogged = true;
            this.isLoginFail = false;
            this.roles = this.tokenService.getAuthorities();
        }
        this.items = [
            {
                label: 'Afiliado Sindical',
                icon: 'ui-icon-person-add',
                routerLink: '/registro'
            },
            {
                label: 'Familiar Afiliado',
                icon: 'ui-icon-people',
                routerLink: '/registroBeneficiarios'
            },
            {
                label: 'Usuario Externo',
                icon: 'ui-icon-person-add',
                routerLink: '/registroMiembros'
            }
        ];
    }
    onLogin(): void {
        this.blockUI.start('Espere por favor...');
        this.usuario = new LoginUsuario(this.form.nombreUsuario, this.form.password);
        this.authService.login(this.usuario).subscribe(data => {
                this.blockUI.stop();
                if (data === null) {
                    this.messageService.add({
                        severity: 'error', summary: 'Error', detail: 'Error de autenticación: ' +
                            'El usuario y contraseña especificadas no pueden localizarse'
                    });
                    return;
                }
                this.tokenService.setToken(data.token);
                this.tokenService.setUserName(data.nick);
                this.tokenService.setAuthorities(data.authorities);

                this.isLogged = true;
                this.isLoginFail = false;
                this.roles = this.tokenService.getAuthorities();
                this.router.navigate(['']);
            },
            (err: any) => {
                this.blockUI.stop();
                this.isLogged = false;
                this.isLoginFail = true;
                this.errorMsg = err.error.message;
                // window.location.reload();
                this.messageService.add({
                    severity: 'error', summary: 'Error', detail: 'Error de autenticación: ' +
                        this.errorMsg
                });
            }
        );
    }
}
