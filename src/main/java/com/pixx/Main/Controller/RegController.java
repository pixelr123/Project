package com.pixx.Main.Controller;

import com.pixx.Main.Model.User;
import com.pixx.Main.Service.EmailService;
import com.pixx.Main.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RegController {
    private static final Logger logger = LoggerFactory.getLogger(RegController.class);

    @Autowired
    private UserService userService;
    //    @Autowired
   //private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private EmailService emailService;

    @PostMapping("/api/addReg")
    public User addReg(@RequestBody User user, HttpServletRequest request) {
        logger.info("addUser: Received request URL:" + request.getRequestURL().toString()
                + ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
        return userService.addReg(user);
    }


    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public ModelAndView displayResetPasswordPage(ModelAndView modelAndView) {

        //List<User> user = userService.findUserByResetToken(token);

        modelAndView.setViewName("fpasswd");
        return modelAndView;
    }



    @RequestMapping(value = "/forgot/{email}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void processForgotPasswordForm(@PathVariable("email") String email, HttpServletRequest request) throws MessagingException {

        List<User> optional = userService.findUserByEmail(email);
        User user = new User();

        user.setResetToken(UUID.randomUUID().toString());

        userService.save(user);

        String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

        user.setEmail("atul.prasad.333@gmail.com");
        SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
        passwordResetEmail.setFrom("support@demo.com");
        passwordResetEmail.setTo(user.getEmail());
        passwordResetEmail.setSubject("Password Reset Request");
        passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
                + "/reset?token=" + user.getResetToken());

        emailService.sendEmail(passwordResetEmail);
    }



    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public void setNewPassword(@RequestBody @PathVariable Map<String, String> requestParams, RedirectAttributes redir) {


        List<User> user = userService.findUserByResetToken(requestParams.get("reset_token"));
        User resetUser = new User();

        //resetUser.setPassword(bCryptPasswordEncoder.encode(requestParams.get("password")));

        resetUser.setResetToken(null);

        userService.save(resetUser);

        //redir.addFlashAttribute("successMessage", "You have successfully reset your password.  You may now login.");

    }
}
