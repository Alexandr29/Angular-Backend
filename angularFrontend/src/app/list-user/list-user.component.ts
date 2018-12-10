import {Component, OnInit, Inject} from '@angular/core';
import {Router} from '@angular/router';
import {User} from '../model/user.model';
import {ApiService} from '../core/api.service';
import {first} from 'rxjs/operators';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.css']
})
export class ListUserComponent implements OnInit {

  users: User[];

  constructor(private router: Router, private apiService: ApiService) {
  }

  ngOnInit() {
     if (!window.localStorage.getItem('token')) {
       this.router.navigate(['login']);
       return;
     }
    this.apiService.getUsers()
      .subscribe(data => {
        this.users = data.user;
      });
  }

  logout(): void {
    // this.apiService.logout();
    window.sessionStorage.clear();
    window.localStorage.clear();
    this.router.navigate(['login']);
  }

  deleteUser(user: User): void {
    this.apiService.deleteUser(user.id)
      .subscribe(data => {
        this.users = this.users.filter(u => u !== user);
      });
  }

  editUser(user: User): void {
    window.localStorage.removeItem('editUserId');
    window.localStorage.setItem('editUserId', user.id.toString());
    this.router.navigate(['edit-user']);
  }

  addUser(): void {
    this.router.navigate(['add-user']);
  }
}
