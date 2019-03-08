import { Component, OnInit } from '@angular/core';
import { Issue } from 'src/app/models/issue';
import { IssuesService } from 'src/app/services/issues.service';

@Component({
  selector: 'app-git',
  templateUrl: './git.component.html',
  styleUrls: ['./git.component.css']
})
export class GitComponent implements OnInit {
  // F I E L D S
  issues: Issue[] = [];
  selected: Issue = null;

  // C O N S T R U C T O R

  constructor(private service: IssuesService) { }

  // O N  I N I T

  ngOnInit() {
    this.service.getAll().subscribe(
      data => {
        this.issues = data;
        this.issues.sort((a, b) => {

          if (a.created_at < b.created_at) {
            return 1;
          }
          return -1;
        });
      }
    );
  }

  // M E T H O D S

  setSelected(issue: Issue) {
    this.selected = issue;
  }

  cancelSelected() {
    this.selected = null;
  }




}
