import { Component, OnInit } from '@angular/core';
import {BreadcrumbService} from '../../../../breadcrumb.service';

@Component({
  selector: 'app-ambulancia',
  templateUrl: './ambulancia.component.html',
  styleUrls: ['./ambulancia.component.css']
})
export class AmbulanciaComponent implements OnInit {

    constructor(private breadcrumbService: BreadcrumbService) {
        this.breadcrumbService.setItems([
            {label: 'Asistencias/Ambulancia', routerLink: ['/asistencia/asistenciaAmbulancia']}
        ]);
    }

  ngOnInit() {
  }

}
