import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {TokenService} from '../../services/security/token.service';
import {ConfirmationService, MessageService} from 'primeng/api';
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'app-logout',
    templateUrl: './logout.component.html',
    styleUrls: ['./logout.component.css'],
    providers: [MessageService,
        ConfirmationService]
})
export class LogoutComponent implements OnInit {
    isLogin = false;
    authority: string;

    constructor(private tokenService: TokenService, private router: Router,
                private confirmationService: ConfirmationService,
                private activatedroute: ActivatedRoute) {
    }

    ngOnInit() {
        this.confirmationService.confirm({
            message: '¿Desea salir de la plataforma?',
            accept: () => {
                this.tokenService.logOut();
                this.isLogin = false;
                this.authority = '';
                this.router.navigate(['home']);
                this.router.navigate(['login']);
            }, reject: () => {
                this.router.navigate([this.activatedroute.snapshot.paramMap.get('href')]);
            }
        });

    }

}
