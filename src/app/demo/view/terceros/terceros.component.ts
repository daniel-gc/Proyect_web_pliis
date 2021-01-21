import { Component, OnInit } from '@angular/core';
import {BreadcrumbService} from '../../../breadcrumb.service';
import {ConfirmationService, MessageService} from 'primeng/api';

@Component({
  selector: 'app-terceros',
  templateUrl: './terceros.component.html',
  styleUrls: ['./terceros.component.css'],
  providers: [MessageService,
        ConfirmationService]
})
export class TercerosComponent implements OnInit {
    display: boolean = false;
    monto: number = 0;
    salarioMensual: number = 0;
    diasAguinaldo: any = 0;
    vacacionesAnuales: number = 0;
    vacacionesPendientes: number = 0;
    fechaIngreso: Date = new Date();
    fechaEgreso: Date = new Date();

    constructor(private breadcrumbService: BreadcrumbService, private messageService: MessageService,
                private confirmationService: ConfirmationService) {
        this.breadcrumbService.setItems([
            { label: 'Servicios de terceros', routerLink: ['/terceros'] }
        ]);
    }

    ngOnInit() {
    }

    onTabChange($event: any) {
    }

    showDialog() {
        const intervalo = this.fechaEgreso.getDate() - this.fechaIngreso.getDate();
        this.monto = (this.diasAguinaldo + this.vacacionesAnuales + this.vacacionesPendientes + this.salarioMensual + 1 ) * intervalo / 100;
        this.display = true;
    }
    closeDialog() {
        this.monto = 0;
        this.display = false;
    }
}
