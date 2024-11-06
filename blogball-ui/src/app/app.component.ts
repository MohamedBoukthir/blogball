import {Component, inject, OnInit} from '@angular/core';
import {FaConfig, FaIconLibrary} from "@fortawesome/angular-fontawesome";
import {fontAwesomeIcons} from "./utils/fontAwesomeIcons";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  title = 'blogball';

  private faIconLibrary= inject(FaIconLibrary);
  private faConfig = inject(FaConfig);

  ngOnInit() {
    this.initFontAwesome();
  }

  initFontAwesome() {
        this.faConfig.defaultPrefix = 'far';
        this.faIconLibrary.addIcons(...fontAwesomeIcons)
    }

}

