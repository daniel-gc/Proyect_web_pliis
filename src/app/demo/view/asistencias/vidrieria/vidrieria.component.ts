import { Component, OnInit } from '@angular/core';
import {BreadcrumbService} from '../../../../breadcrumb.service';

@Component({
  selector: 'app-vidrieria',
  templateUrl: './vidrieria.component.html',
  styleUrls: ['./vidrieria.component.css']
})
export class VidrieriaComponent implements OnInit {

    constructor(private breadcrumbService: BreadcrumbService) {
        this.breadcrumbService.setItems([
            {label: 'Asistencias/Vidriería', routerLink: ['/asistencia/vidrieria']}
        ]);
    }

  ngOnInit() {
  }

}
