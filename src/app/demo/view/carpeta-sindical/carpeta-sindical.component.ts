import {Component, forwardRef, OnInit} from '@angular/core';
import {CarpetaSindicalDTO} from '../../../models/empresas_sindicatos/carpeta-sindical-dto';
import {CarpetaSindicalService} from '../../../services/empresas_sindicatos/carpeta-sindical.service';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {BreadcrumbService} from '../../../breadcrumb.service';
import {ConfirmationService, MessageService} from 'primeng/api';
import {NG_VALUE_ACCESSOR} from '@angular/forms';

@Component({
    selector: 'app-carpeta-sindical',
    templateUrl: './carpeta-sindical.component.html',
    styleUrls: ['./carpeta-sindical.component.css'],
    providers: [MessageService,
        ConfirmationService,
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => CarpetaSindicalComponent),
            multi: true,
        }, ]
})
export class CarpetaSindicalComponent implements OnInit {
    listaCarpetas: CarpetaSindicalDTO[] = [];
    cols: any[];
    @BlockUI() blockUI: NgBlockUI;

    constructor(private messageService: MessageService,
                private confirmationService: ConfirmationService, private breadcrumbService: BreadcrumbService,
                private carpetaSindicalService: CarpetaSindicalService) {
    }

    ngOnInit() {
        this.blockUI.start('Cargando datos iniciales');
        this.carpetaSindicalService.getCarpetaSindical().subscribe(data => {
            this.listaCarpetas = data;
            this.cols = [
                { field: 'nombreEmpresa', header: 'Empresa' },
                { field: 'nombreCentroTrabajo', header: 'Centro de trabajo' },
                { field: 'url', header: 'URL' },
            ];
            this.blockUI.stop();
        }, error => {
            this.blockUI.stop();
        });
    }

}
