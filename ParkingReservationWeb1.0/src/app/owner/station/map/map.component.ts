import { Component, OnInit, Output, EventEmitter } from '@angular/core';

import { MapsAPILoader } from '@agm/core';
import { google } from '@agm/core/services/google-maps-types';


@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {

  constructor(private mapsAPILoader: MapsAPILoader) { }

  zoom: number = 13;

  // initial center position for the map
  lat: number = 10.80754;
  lng: number = 106.6575285;
  markers: MakerModel;
  value = "xx";

  @Output() location: EventEmitter<MakerModel> = new EventEmitter();

  clickedMarker(label: string, index: number) {
    console.log(`clicked the marker: ${label || index}`)
  }

  mapClicked($event: any) {
    this.markers = {
      lat: $event.coords.lat,
      lng: $event.coords.lng,
      draggable: false
    };
    this.location.emit(this.markers);
  }

  mapSearch(name: String) {

    this.mapsAPILoader.load().then(() => {

      var geocoder = new google.maps.Geocoder();
      geocoder.geocode({ 'address': name }, (results, status) => {
        this.markers = {
          lat: results[0].geometry.location.lat(),
          lng: results[0].geometry.location.lng(),
          draggable: false
        }
      })
      this.value = ""
    });
  }


  ngOnInit() {
    this, this.mapsAPILoader.load()
  }

}

export interface MakerModel {
  lat: number;
  lng: number;
  label?: string;
  draggable: boolean;
}
