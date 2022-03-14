/**
 * @author UWENAYO Alain Pacifique
 * @description RegisterView class
 * @date 2022-03-06
 **/

package views;

import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.MessageTypes;
import models.RegisterUser;
import models.RequestBody;
import models.UserUtils.UserGender;
import models.UserUtils.UserRoles;
import utils.registration.Handlers;
import utils.registration.Validations;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static utils.MessagePrinter.printConsoleMessage;

/** The type RegisterUser view. */
public class RegisterView {

  /**
   * Register user.
   *
   * @throws Exception the exception
   * @description register a new user
   * @author UWENAYO ALain Pacifique
   */
  public static void registerUser() throws Exception {

        Scanner scanner = new Scanner(System.in);
        UserRoles userRole = UserRoles.EMPLOYER;
        String firstName;
        String lastName = "";
        UserGender userGender = UserGender.MALE;
        printConsoleMessage(MessageTypes.NORMAL, false, "\tUSER REGISTER");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter your first name[ex:UWENAYO ALain Pacifique]");
        firstName = scanner.nextLine();
        while(!Validations.isNameValid(firstName).equals("OK")){
            printConsoleMessage(MessageTypes.ERROR, false, Validations.isNameValid(firstName));
            System.out.println(firstName);
        }
        int genderChoice = scanner.nextInt();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter your gender[1:Male,2:Female]");
        while(Validations.inRange(genderChoice,1,2).equals("OK")){
            printConsoleMessage(MessageTypes.ERROR,false,Validations.inRange(genderChoice,1,2));
            genderChoice = scanner.nextInt();
        }
        if(genderChoice == 2) userGender = UserGender.FEMALE;
        // solving the know bug for using nextInt()
        scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter your email[ex:example@domain.com]");
        String email = scanner.nextLine();
        while(!Validations.isEmailValid(email).equals("OK")){
            printConsoleMessage(MessageTypes.ERROR, false, Validations.isEmailValid(email));
            email = scanner.nextLine();
        }
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter your password[strong password suggested]");
        String password = scanner.nextLine();
        while(!Validations.isPasswordValid(password).equals("OK")){
            printConsoleMessage(MessageTypes.ERROR, false, Validations.isPasswordValid(password));
            password = scanner.nextLine();
        }
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter your Date of Birth[ex:01/12/1990]");
        String inputDOB = scanner.nextLine();
        while(!Validations.isDateValid(inputDOB).equals("OK")){
            printConsoleMessage(MessageTypes.ERROR, false, Validations.isDateValid(inputDOB));
            inputDOB = scanner.nextLine();
        }
        Date dateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine());
        while(!Validations.isDateOfBirthValid(dateOfBirth).equals("OK")){
            printConsoleMessage(MessageTypes.ERROR, false, Validations.isDateOfBirthValid(dateOfBirth));
            dateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine());
        }
        printConsoleMessage(MessageTypes.NORMAL, false,"\tChoose account type[1:employer,2:employee]");
        int accountType = scanner.nextInt();
        while(Validations.inRange(accountType,1,2).equals("OK")){
            printConsoleMessage(MessageTypes.ERROR,false,Validations.inRange(accountType,1,2));
            accountType = scanner.nextInt();
        }
        if (accountType == 2){
            userRole = UserRoles.EMPLOYEE;
        }
        if(firstName.contains(" ")) lastName = firstName.substring(firstName.indexOf(" ")+1);
        firstName = firstName.substring(0,firstName.indexOf(" "));
        RegisterUser user = new RegisterUser(1,firstName,lastName,email,password,userGender,userRole, dateOfBirth);
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/users");
        requestBody.setAction("register");
        requestBody.setObject(user);

        String requestString = new ObjectMapper().writeValueAsString(requestBody);
        System.out.println(requestString);
        Handlers.RequestHandler(requestString);
    }

}