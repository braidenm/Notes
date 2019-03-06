import { Pipe, PipeTransform } from '@angular/core';
import { Note } from '../models/note';

@Pipe({
  name: 'incomplete'
})
export class IncompletePipe implements PipeTransform {

  transform(notes: Note[], all: boolean): Note[] {
    const result: Note[] = [];
    if (all) {
      return notes;
    }
    for (const note of notes) {
        if (note.completed === false) {
          result.push(note);
        }
    }
    return result;
  }

}
