import {Component, OnInit, Inject} from '@angular/core';
import {Router} from '@angular/router';
import {User} from '../model/user.model';
import {ApiService} from '../core/api.service';
import {first} from 'rxjs/operators';

@Component({
  selector: 'app-list-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  users: User[];

  constructor(private router: Router, private apiService: ApiService) {
  }

  ngOnInit() {
  }

  logout(): void {
    this.router.navigate(['login']);
  }
}
