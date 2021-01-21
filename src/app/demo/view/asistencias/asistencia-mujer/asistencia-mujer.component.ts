import { Component, OnInit } from '@angular/core';
import {BreadcrumbService} from '../../../../breadcrumb.service';

@Component({
  selector: 'app-asistencia-mujer',
  templateUrl: './asistencia-mujer.component.html',
  styleUrls: ['./asistencia-mujer.component.css']
})
export class AsistenciaMujerComponent implements OnInit {

    constructor(private breadcrumbService: BreadcrumbService) {
        this.breadcrumbService.setItems([
            {label: 'Asistencias/A la mujer', routerLink: ['/asistencia/asistenciaMujer']}
        ]);
    }

  ngOnInit() {
  }

}
