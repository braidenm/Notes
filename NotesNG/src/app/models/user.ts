import { Note } from './note';

export class User {

  // F E I L D S

  id: number;
  firstName: string;
  lastName: string;
  username: string;
  password: string;
  email: string;
  enabled: boolean;
  role: string;
  notes: Note[];

  // C O N S T R U C T O R S

  constructor( id?: number, firstName?: string, lastName?: string, enabled?: boolean,
               username?: string, password?: string, email?: string, role?: string) {

      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.username = username;
      this.password = password;
      this.email = email;
      this.enabled = enabled;
      this.role = role;
  }
}
