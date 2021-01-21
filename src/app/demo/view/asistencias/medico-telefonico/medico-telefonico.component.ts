import { Component, OnInit } from '@angular/core';
import {BreadcrumbService} from '../../../../breadcrumb.service';

@Component({
  selector: 'app-medico-telefonico',
  templateUrl: './medico-telefonico.component.html',
  styleUrls: ['./medico-telefonico.component.css']
})
export class MedicoTelefonicoComponent implements OnInit {

    constructor(private breadcrumbService: BreadcrumbService) {
        this.breadcrumbService.setItems([
            {label: 'Asistencias/Médico Telefónico', routerLink: ['/asistencia/medicoTelefonico']}
        ]);
    }

  ngOnInit() {
  }

}
