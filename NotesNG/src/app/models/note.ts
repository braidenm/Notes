import { User } from './user';

export class Note {

  // F E I L D S

  id: number;
  title: string;
  details: string;
  completed: boolean;
  created: string;
  updated: string;
  user: User;

  // C O N S T R U C T O R S

  constructor(id?: number, title?: string, details?: string, completed?: boolean,
              created?: string, updated?: string, user?: User) {

      this.id = id;
      this.title = title;
      this.details = details;
      this.completed = completed;
      this.created = created;
      this.updated = updated;
      this.user = user;
  }

}
