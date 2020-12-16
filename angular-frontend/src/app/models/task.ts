export class Task{

    taskId: number;
    taskName: string;
    taskCategory: string;
    taskStatus: string;
    description: string;
    taskPriority: string;
    employee: string;
    employeeId: number;
    taskCompletedDate: string;
    manager: string;
    managerId : number;
    taskSubmittedDate: string;
    taskDueDate: string;

    constructor(   taskId: number,
        taskName: string,
        taskCategory: string,
        taskStatus: string,
        description: string,
        taskPriority: string,
        employee: string,
        employeeId: number,
        taskCompletedDate: string,
        manager: string,
        managerId: number,
        taskSubmittedDate: string,
        taskDueDate: string){

        this.taskId = taskId;
		this.taskName = taskName;
		this.taskCategory = taskCategory;
		this.taskStatus = taskStatus;
		this.description = description;
		this.taskPriority = taskPriority;
        this.employee = employee;
        this.employeeId = employeeId;
		this.taskCompletedDate = taskCompletedDate;
        this.manager = manager;
        this.managerId = managerId;
		this.taskSubmittedDate = taskSubmittedDate;
        this.taskDueDate = taskDueDate;
        
    }
    

}
