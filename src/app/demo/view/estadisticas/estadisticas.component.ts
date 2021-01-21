import { Component, OnInit } from '@angular/core';
import {BreadcrumbService} from '../../../breadcrumb.service';
import {ConfirmationService, Message, MessageService} from 'primeng/api';

@Component({
  selector: 'app-estadisticas',
  templateUrl: './estadisticas.component.html',
  styleUrls: ['./estadisticas.component.css'],
  providers: [MessageService,
      ConfirmationService]
})
export class EstadisticasComponent implements OnInit {
    msgs: Message[] = [];
    selectedCausas: string[] = [];

    constructor(private breadcrumbService: BreadcrumbService, private messageService: MessageService,
                private confirmationService: ConfirmationService) {
        this.breadcrumbService.setItems([
            { label: 'Estadísticas del sistema', routerLink: ['/estadisticas'] }
        ]);

    }

  ngOnInit() {
  }

    onTabChange(event) {
        // this.messageService.add({severity: 'info', summary: 'Tab Expanded', detail: 'Index: ' + event.index});
    }

    confirm1() {
        this.confirmationService.confirm({
            message: '¿Está seguro de proceder con la denuncia formulada?',
            header: 'Confirmación de denuncia',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
                this.msgs = [{severity: 'info', summary: 'Confirmación:', detail: 'Su denuncia ha sido enviada'}];
                this.messageService.add({severity: 'info', summary: 'Confirmado', detail: 'Su denuncia ha sido enviada'});
            },
            reject: () => {
                this.msgs = [{severity: 'info', summary: 'Cancelación:', detail: 'Usted ha cancelado la denuncia'}];
                this.messageService.add({severity: 'info', summary: 'Cancelada', detail: 'Usted ha cancelado la denuncia'});
            }
        });
    }
}
