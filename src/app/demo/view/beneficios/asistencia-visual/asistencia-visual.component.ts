import { Component, OnInit } from '@angular/core';
import {BreadcrumbService} from '../../../../breadcrumb.service';

@Component({
  selector: 'app-asistencia-visual',
  templateUrl: './asistencia-visual.component.html',
  styleUrls: ['./asistencia-visual.component.css']
})
export class AsistenciaVisualComponent implements OnInit {

    constructor(private breadcrumbService: BreadcrumbService) {
        this.breadcrumbService.setItems([
            {label: 'Asistencias/Asistencia Visual', routerLink: ['/beneficios/asistenciaVisual']}
        ]);
    }

  ngOnInit() {
  }

}
