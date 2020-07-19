import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CinemaService {
  public host="http://localhost:8080"
  constructor(private http:HttpClient) { }
  public VillesList(){
    return this.http.get(this.host+"/villes");
  }

  public getOneCinema(city) {
    return this.http.get(city._links.cinemas.href);

  }

  getSalles(cinema) {
    return this.http.get(cinema._links.salles.href)

  }

  getOneProjection(salle) {
    let url=salle._links.projectionFilms.href.replace("{?projection}","")
    console.log(url+"?projection=1")
    return this.http.get(url+"?projection=p1")
  }

  // getoneProjectionDetails(projection) {
  //   return this.http.get(projection.self.href)
  //
  // }

  public getTicketPlaces(p){
    let url = p._links.tickets.href.replace("{?projection}","")
    return this.http.get(url+"?projection=ticket")
  }

  payTickets(value: any) {
      return this.http.post(this.host+"/payerTickets",value);


  }
}
