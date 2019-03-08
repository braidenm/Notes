import { User } from './user';

export class Issue {
  title: string;
  body: string;
  user: User;
  assignee: User;
  // tslint:disable-next-line:variable-name
  created_at: Date;
}
