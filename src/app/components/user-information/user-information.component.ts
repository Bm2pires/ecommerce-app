import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { UserDetails } from 'src/app/services/userDetails';

@Component({
  selector: 'app-user-information',
  templateUrl: './user-information.component.html',
  styleUrls: ['./user-information.component.css']
})
export class UserInformationComponent implements OnInit {

  errors: Array<string> = [];
  valid = true


    emailNew: string = "Current";
    titleNew: string = "Mr"
    firstnameNew: string = "Current"
    lastnameNew:string  = "Current"
    dateofbirthNew: string | null = new Date().toLocaleDateString();
    contactnumberNew: string = "Current"
    addressNew:string = "Current"
    passwordNew:string = "Current"
    phoneNumberNew:string = "Current"

    newUserDetails: UserDetails = {
      id: 0,
      firstName: this.firstnameNew,
      lastName: this.lastnameNew,
      email: this.emailNew,
      password: this.passwordNew,
      title: this.titleNew,
      dateOfBirth: this.dateofbirthNew,
      phoneNumber: this.phoneNumberNew,
      address: this.addressNew
    };

    oldUserDetails: UserDetails = {
      id: 0,
      firstName: '',
      lastName: '',
      email: '',
      password: '',
      title: '',
      dateOfBirth: new Date().toLocaleDateString(),
      phoneNumber: '',
      address: ''
    };


    disabledFields = true;

    titles = ['Mr', 'Mrs',
    'Miss', 'Ms'];

    submitted = false;



  constructor(private datePipe: DatePipe, private userService: UserService) {

  }

  ngOnInit(): void {
    this.newUserDetails.dateOfBirth = this.datePipe.transform(this.newUserDetails.dateOfBirth,"yyyy-MM-dd")
  }

   disableFunc() {
    this.disabledFields = false;

    this.oldUserDetails.email = this.newUserDetails.email;
    this.oldUserDetails.title = this.newUserDetails.title;
    this.oldUserDetails.firstName = this.newUserDetails.firstName;
    this.oldUserDetails.lastName = this.newUserDetails.lastName;
    this.oldUserDetails.dateOfBirth = this.newUserDetails.dateOfBirth
    this.oldUserDetails.phoneNumber = this.newUserDetails.phoneNumber;
    this.oldUserDetails.address = this.newUserDetails.address;
  }

  reset() {
    this.disabledFields = true;


    this.newUserDetails.email = this.oldUserDetails.email;
    this.newUserDetails.title =this.oldUserDetails.title;
    this.newUserDetails.firstName=this.oldUserDetails.firstName;
    this.newUserDetails.lastName=this.oldUserDetails.lastName;
    this.newUserDetails.dateOfBirth=this.oldUserDetails.dateOfBirth;
    this.newUserDetails.phoneNumber=this.oldUserDetails.phoneNumber;
    this.newUserDetails.address=this.oldUserDetails.address;

    this.oldUserDetails.email = '';
    this.oldUserDetails.title = '';
    this.oldUserDetails.firstName = '';
    this.oldUserDetails.lastName = '';
    this.oldUserDetails.dateOfBirth = new Date().toLocaleDateString();
    this.oldUserDetails.phoneNumber = '';
    this.oldUserDetails.address = '';
  }

  onSubmit() {
    this.submitted = true;
    this.validate();
      if(this.valid){
        this.userService.edituser(this.newUserDetails).subscribe(data => {
          console.log(data);
        });
        this.ngOnInit();
        this.disabledFields = true;
        this.reset();
    }else{
      alert(this.errors)
      this.errors = [];
    }


    //Need to update backend


  }

  validate() {
    const phoneNumberCheck = Number(this.newUserDetails.phoneNumber);
    if(Number.isNaN(phoneNumberCheck)){
      this.errors.push("Phone number must be digitis");
    }
    if(this.newUserDetails.phoneNumber.length != 10){
      this.errors.push("Phone number must be 10 digitis");
    }
    const emailCheck = Array.from(this.newUserDetails.email);
    let emailValid = false;
    emailCheck.forEach((letter) => {
      if(letter === '@'){
        emailValid = true;
      }
    })
    if(!emailValid){
      this.errors.push("Email must contain an @");
    }

    const dateCheck = this.newUserDetails.dateOfBirth!.toString();
    let today = this.datePipe.transform(Date.now(),'yyyy-MM-dd')!;

    if(dateCheck > today){
      this.errors.push("Date of birth cannot be in the future");
    }
    let today2 = new Date();
    var birthDate = new Date(this.newUserDetails.dateOfBirth!);
    var age = today2.getFullYear() - birthDate.getFullYear();
    var m = today2.getMonth() - birthDate.getMonth();
    if (m < 0 || (m === 0 && today2.getDate() < birthDate.getDate())) {
        age--;
    }

    if(age < 18){
      this.errors.push("Age must be 18 or older");
    }


    if(this.errors.length != 0){
      this.valid = false;
    }else{
      this.valid = true;
    }
  }

  // checkInput(input: { valid: any; }){
  //   if(input.valid){
  //     return false;
  //   }else {
  //     return true
  //   }
  // }

}
