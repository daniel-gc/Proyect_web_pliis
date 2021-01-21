import {Component, OnInit} from '@angular/core';
import {BreadcrumbService} from '../../../../breadcrumb.service';
import {CambioPasswDTO} from '../../../../models/generales/cambio-passw-dto';
import {ApplicationInitService} from '../../../../services/application-init.service';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {ConfirmationService, MessageService} from 'primeng/api';

@Component({
    selector: 'app-cambio-passw',
    templateUrl: './cambio-passw.component.html',
    styleUrls: ['./cambio-passw.component.css'],
    providers: [MessageService,
        ConfirmationService]
})
export class CambioPasswComponent implements OnInit {
    pass0: string = null;
    pass1: string = null;
    match = true;
    cambioPasswDTO: CambioPasswDTO = new CambioPasswDTO();
    @BlockUI() blockUI: NgBlockUI;

    constructor(private breadcrumbService: BreadcrumbService, private applicationInitService: ApplicationInitService,
                private messageService: MessageService) {
        this.breadcrumbService.setItems([
            {label: 'Cambio de contraseña'},
        ]);
    }


    ngOnInit() {
    }

    mouseOutPass(event) {
        this.match = this.pass0 === this.pass1;
    }

    cambiarContr() {
        if (this.pass0 !== this.pass1) {
            this.messageService.add({severity: 'error', summary: 'Error', detail: 'Las contraseñas no coinciden.'});
            return;
        }
        this.cambioPasswDTO.nuevaContr = this.pass0;
        this.applicationInitService.cambiarContrasena(this.cambioPasswDTO).subscribe(ret => {
            this.messageService.add({
                severity: 'success', summary: 'Operación exitosa',
                detail: 'Se cambió su contraseña satisfactoriamente.'
            });
        }, error => {
            this.messageService.add({severity: 'error', summary: 'Error', detail: 'Ocurrió el siguiente error:' + error.error.Mensaje});
        });
    }
}
