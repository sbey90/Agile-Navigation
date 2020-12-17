export class User{
    
    userId: number;
    username: string;
    password: string;
    confirmPassword: string;
    firstName: string;
    lastName: string;
    email: string;
    hireDate : string;
    role: string;
    salary: number;
    
    constructor(userId: number, username: string, password: string, 
        confirmPassword: string, firstName: string, lastName: string, 
        email: string, hireDate: string, role:string, salary: number){
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hireDate = hireDate;
        this.role = role;
        this.salary = salary;
    }
       
}