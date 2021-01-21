import {Component, forwardRef, OnInit} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {NG_VALUE_ACCESSOR} from '@angular/forms';
import {OlvidePasswDTO} from '../../../models/generales/olvide-passw-dto';
import {ApplicationInitService} from '../../../services/application-init.service';

@Component({
    selector: 'app-olvide-passw',
    templateUrl: './olvide-passw.component.html',
    styleUrls: ['./olvide-passw.component.css'],
    providers: [MessageService,
        ConfirmationService,
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => OlvidePasswComponent),
            multi: true,
        }]
})
export class OlvidePasswComponent implements OnInit {
    emailAfiliado: string;
    pass0: string;
    pass1: string;
    @BlockUI() blockUI: NgBlockUI;
    display = false;
    token: string;
    olvidePasswDTO: OlvidePasswDTO = new OlvidePasswDTO();
    match: boolean;
    tokenSolicicitado = false;

    constructor(private messageService: MessageService,
                private confirmationService: ConfirmationService,
                private applicationInitService: ApplicationInitService) {
    }

    ngOnInit() {
    }

    onGenerarToken() {
        this.blockUI.start('Espere por favor');
        this.applicationInitService.recuperaPasswOlvidada(this.emailAfiliado).subscribe(data => {
            this.blockUI.stop();
            this.tokenSolicicitado = data;
            if (data) {
                this.messageService.add({severity: 'success',
                    summary: 'Operación exitosa', detail: 'Se enviaron las instrucciones a su email.'});
            } else {
                this.messageService.add({
                    severity: 'error', summary: 'Error',
                    detail: 'No pudo enviarse las instrucciones a su email. Contacte a su administrador'
                });
            }
        }, error => {
            this.blockUI.stop();
            this.messageService.add({
                severity: 'error', summary: 'Error',
                detail: 'No pudo enviarse las instrucciones a su email. Contacte a su administrador: ' + error.error.Mensaje
            });
        });
    }

    emailValido(): boolean {
        const emailVerif = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        return emailVerif.test(this.emailAfiliado);
    }

    closeDialog() {

    }

    mouseOutPass(event) {
        if (this.pass0 !== this.pass1) {
            this.match = false;
        } else {
            this.match = true;
        }
    }

    cambiaPassw() {
        this.blockUI.start('Espere por favor');
        this.olvidePasswDTO = new OlvidePasswDTO();
        this.olvidePasswDTO.nuevaPassw = this.pass0;
        this.olvidePasswDTO.token = this.token.trim();
        this.applicationInitService.cambiaPasswOlvidada(this.olvidePasswDTO).subscribe(ret => {
            this.blockUI.stop();
            if (ret) {
                this.messageService.add({severity: 'success',
                    summary: 'Operación exitosa', detail: 'Su contraseña ha sido cambiada.'});
            } else {
                this.messageService.add({
                    severity: 'error', summary: 'Error',
                    detail: 'No pudo cambiarse su contraseña, refresque la página e intente de nuevo.Si el problema presiste, contacte a su administrador. '
                });
            }
        }, error => {
            this.blockUI.stop();
            this.messageService.add({
                severity: 'error', summary: 'Error',
                detail: 'No pudo cambiarse su contraseña: ' + error.error.Mensaje
            });
        });
    }
}
