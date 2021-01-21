import {Component, forwardRef, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BreadcrumbService} from '../../../../breadcrumb.service';
import {LogImportacionDTO} from '../../../../models/empresas_sindicatos/log-importacion-dto';
import {ImportaDatosService} from '../../../../services/empresas_sindicatos/importa-datos.service';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {ConfirmationService, MessageService} from 'primeng/api';
import {NG_VALUE_ACCESSOR} from '@angular/forms';

@Component({
  selector: 'app-importa-datos',
  templateUrl: './importa-datos.component.html',
  styleUrls: ['./importa-datos.component.css'],
    providers: [MessageService,
        ConfirmationService,
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => ImportaDatosComponent),
            multi: true,
        }, ]
})
export class ImportaDatosComponent implements OnInit {

    listaLog: LogImportacionDTO[] = [];
    @BlockUI() blockUI: NgBlockUI;
    cols: any[];

  constructor(private breadcrumbService: BreadcrumbService, private importaDatosService: ImportaDatosService,
              private messageService: MessageService,
              private confirmationService: ConfirmationService) {
      this.breadcrumbService.setItems([
          {label: 'Importar datos', routerLink: ['/importaDatos']}
      ]);
  }

  ngOnInit(): void {
      this.cols = [
          { field: 'idLogImportacion', header: 'ID' },
          { field: 'momento', header: 'MOMENTO' },
          { field: 'motivo', header: 'TIPO' },
          { field: 'texto', header: 'DESCRIPCIÓN' }
      ];
  }

  importaDatos(): void {
      this.blockUI.start('Cargando datos...');
      this.importaDatosService.getImportaDatos().subscribe(data => {
          this.blockUI.stop();
          this.messageService.add({severity: 'success', summary: 'Operación exitosa',
              detail: 'Se importaron los datos de los ficheros correctamente'});
          this.listaLog = data;
      }, error => {
          this.blockUI.stop();
          this.messageService.add({severity: 'error', summary: 'Error',
              detail: 'Ocurrió un error al intentar importar los datos: ' + error.error.Mensaje
          });
      });
    }

}
