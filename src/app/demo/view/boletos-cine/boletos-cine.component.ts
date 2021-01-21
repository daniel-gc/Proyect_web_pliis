import {Component, forwardRef, OnInit} from '@angular/core';
import {ConfirmationService, Message, MessageService, SelectItem} from 'primeng/api';
import {NG_VALUE_ACCESSOR} from '@angular/forms';
import {BreadcrumbService} from '../../../breadcrumb.service';
import {ApplicationInitService} from '../../../services/application-init.service';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {Router} from '@angular/router';
import {TokenService} from '../../../services/security/token.service';
import html2canvas from 'html2canvas';
import {BoletoCineDTO} from '../../../models/generales/boleto-cine-dto';
@Component({
    selector: 'app-boletos-cine',
    templateUrl: './boletos-cine.component.html',
    styleUrls: ['./boletos-cine.component.css'],
    providers: [MessageService,
        ConfirmationService,
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => BoletosCineComponent),
            multi: true,
        }, ]
})
export class BoletosCineComponent implements OnInit {
    constructor(
        private breadcrumbService: BreadcrumbService,
        private messageService: MessageService,
        private confirmationService: ConfirmationService,
        private applicationInitService: ApplicationInitService,
        private router: Router,
        private tokenService: TokenService) {
        this.breadcrumbService.setItems([
            {label: 'Canjear boletos de cine', routerLink: ['/boletosCine']}
        ]);
    }

    @BlockUI() blockUI: NgBlockUI;
    boletoCineDTO: BoletoCineDTO;
    boletoCineDTOerror: BoletoCineDTO;
    tipoUsuario: string;
    mostrarSolicitaBoletoCine: boolean;
    mostrarBoletoCine: boolean;
    mostrarBoletoCineError: boolean;
    correoEnviado: boolean;
    imgcreada = false;
    imagenCreada;
    imagenCreadaBlob;

    ngOnInit() {
        this.tipoUsuario = '';
        this.mostrarSolicitaBoletoCine = true;
        this.mostrarBoletoCine = false;
        this.mostrarBoletoCineError = false;

        // this.mostrarSolicitaBoletoCine = false;
        // this.mostrarBoletoCine = true;
        // this.mostrarBoletoCineError = true;
        // setTimeout(() => {
        //     this.crearImagen();
        // }, 5000);

        this.boletoCineDTO = new BoletoCineDTO();
    }

    crearImagen() {
        html2canvas(document.querySelector('#boletoCine')).then(canvas => {
            this.imagenCreada = canvas.toDataURL();
        });
        this.imgcreada = true;

        setTimeout(() => {
            this.descargarImagen();
        }, 5000);
    }

    descargarImagen() {
        const enlace = document.createElement('a');
        enlace.href = this.imagenCreada;
        enlace.download = 'boletoCine.jpeg';
        document.body.appendChild(enlace);
        enlace.click();
        // Borrrar el elemento
        enlace.parentNode.removeChild(enlace);
    }

    onSolicitarBoleto() {
        if (this.tokenService.getAuthorities().indexOf('ROLE_AFILIADO') !== -1) {
            this.tipoUsuario = 'AS';
        } else if (this.tokenService.getAuthorities().indexOf('ROLE_FAMILIAR') !== -1) {
            this.tipoUsuario = 'FA';
        } else if (this.tokenService.getAuthorities().indexOf('ROLE_MIEMBRO_ACTIVO') !== -1) {
            this.tipoUsuario = 'UE';
        }
        this.blockUI.start('Cargando...');
        this.applicationInitService.solicitarBoleto(this.tipoUsuario).subscribe(data0 => {
            if (data0.error !== 0 && data0.error !== null) {
                this.boletoCineDTO = null;
                this.boletoCineDTOerror = data0;

                this.mostrarSolicitaBoletoCine = false;
                this.mostrarBoletoCine = false;
                this.mostrarBoletoCineError = true;
            } else {
                this.boletoCineDTO = data0;
                this.boletoCineDTOerror = null;

                this.mostrarSolicitaBoletoCine = false;
                this.mostrarBoletoCine = true;
                this.mostrarBoletoCineError = false;

                setTimeout(() => {
                    this.crearImagen();
                }, 100);
            }
            this.blockUI.stop();
        }, error => {
            this.blockUI.stop();
            this.boletoCineDTO = null;
            this.boletoCineDTOerror = null;
            // this.mostrarSolicitaBoletoCine = false;
            // this.mostrarBoletoCine = false;
            // this.mostrarBoletoCineError = true;


            this.messageService.add({
                severity: 'error',
                summary: 'Error',
                detail: 'Ocurrió un error al solicitar el boleto de cine.' +
                    ' Vuelva a cargar la página, si el problema persiste, contacte a su administrador'});
        });
    }
}
