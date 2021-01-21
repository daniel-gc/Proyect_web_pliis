import { Component, OnInit } from '@angular/core';
import {BreadcrumbService} from '../../../../breadcrumb.service';

@Component({
  selector: 'app-checkup-medico',
  templateUrl: './checkup-medico.component.html',
  styleUrls: ['./checkup-medico.component.css']
})
export class CheckupMedicoComponent implements OnInit {

    constructor(private breadcrumbService: BreadcrumbService) {
        this.breadcrumbService.setItems([
            {label: 'Asistencias/Checkup médico', routerLink: ['/asistencia/checkup']}
        ]);
    }

  ngOnInit() {
  }

}
