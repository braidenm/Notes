import { IncompletePipe } from './../../pipes/incomplete.pipe';
import { Component, OnInit } from '@angular/core';
import { Note } from 'src/app/models/note';
import { NoteService } from 'src/app/services/note.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { NgForm } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.css']
})
export class NotesComponent implements OnInit {

  // F I E L D S

  title = 'Notes';
  selected = null;
  notes: Note[] = [];
  showComplete = false;
  noteLevel = 0;
  newNote: Note = new Note(0, '',  '', false);
  editNote: Note = null;
  search = '';
  count = function() {
    return this.incomplete.transform(this.notes).length;
  };
  displayNote = function(note) {
    this.selected = note;
  };

  displayTable = function() {
    this.selected = null;
  };

  // C O N S T R U C T O R
  constructor(private noteService: NoteService, private auth: AuthService,
              private route: ActivatedRoute, private router: Router,
              private dPipe: DatePipe, private incomplete: IncompletePipe) { }

  // O N  I N I T

  ngOnInit() {
    if (!this.selected && this.route.snapshot.paramMap.get('id')) {
      const id = this.route.snapshot.paramMap.get('id');
      this.noteService.show(parseInt(id, 10)).subscribe(
        data => {
          this.selected = data;
        },
        err => {
          this.router.navigateByUrl('notFound');
        }
      );
   }
    if (!this.auth.checkLogin()) {
       this.router.navigateByUrl('/register');
   }
    this.reload();
  }

  // P U B L I C  M E T H O D S

  reload() {
    this.search = '';
    this.noteService.index().subscribe(
      data => {
        this.notes = data.sort((a, b) => {
        if (a.updated > b.updated) {
          return -1;
        }
        return 1;
      });

    },

      err => console.error('notes got errors: ' + err)
    );
  }

  searchNotes(kword: string) {
    if(kword === '' || kword === null) {
      this.reload();
    }
    this.noteService.search(kword).subscribe(
      data => {
          this.notes = data.sort((a, b) => {
            if (a.updated > b.updated) {
              return -1;
            }
            return 1;
          });
      }
    );
  }

  addNote(form: NgForm) {
    this.newNote.completed = false;
    this.newNote.details = '';
    console.log(form.value.title);

    this.newNote.title = form.value.title;
    this.noteService.addNote(this.newNote).subscribe(
      data => {
        this.newNote = new Note(0, '', '', false);
        this.reload();
        form.reset();
      },
      err => console.error('Observer got an error: ' + err)
    );
}

  setEditNote() {
    this.editNote = Object.assign({}, this.selected);

  }
  cancelNote() {
    this.editNote = null;
  }
  updateNote() {
      this.selected = this.editNote;
      if (this.selected.completed === true && !this.selected.completedDate) {
        this.selected.completedDate = this.dPipe.transform(Date.now(), 'shortDate');
      } else {
        this.selected.completedDate = '';
      }
      this.noteService.updateNote(this.selected).subscribe(
        data => {
          this.editNote = null;

          this.reload();
        },
        err => console.error('Observer got an error: ' + err)
      );

    }
  updateBoxNote(note: Note) {
    note.completed = !(note.completed);
    this.noteService.updateNote(note).subscribe(
        data => {
          this.reload();
        },
        err => console.error('Observer got an error: ' + err)
      );

    }

  deleteNote(id) {
      this.noteService.deleteNote(id).subscribe(
        data => {
          this.reload();
        },
        err => console.error('Observer got an error: ' + err)
      );
  }

  dangerLevel() {
    if (this.count() >= 6) {
      return 'danger';
    }
    if (this.count() >= 4) {
      return 'warning';
    }
    return 'safe';

  }

  strikeout(note: Note) {
    if (note.completed) {
      return 'strikeout';
    }
  }
}
