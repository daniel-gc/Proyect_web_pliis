import { Component, OnInit } from '@angular/core';
import {BreadcrumbService} from '../../../../breadcrumb.service';

@Component({
  selector: 'app-cerrajeria',
  templateUrl: './cerrajeria.component.html',
  styleUrls: ['./cerrajeria.component.css']
})
export class CerrajeriaComponent implements OnInit {

    constructor(private breadcrumbService: BreadcrumbService) {
        this.breadcrumbService.setItems([
            {label: 'Asistencias/Cerrajería', routerLink: ['/asistencia/cerrajeria']}
        ]);
    }

  ngOnInit() {
  }

}
