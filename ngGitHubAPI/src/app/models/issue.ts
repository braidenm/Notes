import { User } from './user';

export class Issue {
  title: string;
  body: string;
  user: User;
  assignee: User;
  // tslint:disable-next-line:variable-name
  updated_at: Date;
}
