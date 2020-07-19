import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CinemaService} from "../services/cinema.service";
import {forEachComment} from "tslint";

@Component({
  selector: 'app-cinema',
  templateUrl: './cinema.component.html',
  styleUrls: ['./cinema.component.css']
})
export class CinemaComponent implements OnInit {

  public  villes;
  public  cinemas;
  public  salles;
  public currentville;
  public currentCinema;
  public currentsalle;
  public currentProjection;
  public selectedTickets: any[];
  constructor(public  cinemaservice:CinemaService) {


  }

  ngOnInit(): void {
    //Getting data from the backend
    this.cinemaservice.VillesList().subscribe(data=>{
    this.villes=data;
    },error => {
console.log(error);
    })
  }

  onGetCinema(city) {
    this.currentville=city;
    this.cinemaservice.getOneCinema(city).subscribe(data=>{
      this.cinemas=data;
    },error =>{
      console.log(error)
    })

  }

  getsalles(cinema) {
    this.currentCinema=cinema
    this.cinemaservice.getSalles(cinema).subscribe(data=>{
      console.log(data);
      this.salles=data;
      this.salles._embedded.salles.forEach(salle=>{
        this.cinemaservice.getOneProjection(salle).subscribe(data=>{
          salle.projection=data;
          console.log(data);
        },error=>{
          console.log(error)
        })
      })
    },error =>{
      console.log(error)
    })

  }

  onGetTicketPlaces(p: any) {
    this.currentProjection = p
    console.log(this.currentProjection)
    this.cinemaservice.getTicketPlaces(p)
      .subscribe(data=>{
        console.log(data)
        this.currentProjection.tickets=data
        this.selectedTickets=[]
      },err=>{
        console.log(err)
      })
  }

  onSelectTicket(t: any) {
    if(!t.selected){
      t.selected=true
      this.selectedTickets.push(t)
    }
    else{
      t.selected=false
      this.selectedTickets.splice(this.selectedTickets.indexOf(t),1)
    }
  }

  getTicketClass(t: any) {
    let str = "btn tickets "
    if(t.reserve==true){
      str+="btn-danger"
    }
    else if(t.selected){
      str+="btn-warning"
    }
    else{
      str+="btn-success"
    }
    return str
  }

  onPayTickets(value: any) {
      let tickets=[]
      this.selectedTickets.forEach(t => {
        tickets.push(t.id)
      });
      value.tickets=tickets
      this.cinemaservice.payTickets(value)
        .subscribe(data=>{
          alert('ticket reserved')
          this.onGetTicketPlaces(this.currentProjection)
        },err=>{
          console.log(err)
        })
    }


}
