package com.step.springmvcapp.controller;

import com.step.springmvcapp.entity.Candidate;
import com.step.springmvcapp.entity.CandidateDetails;
import com.step.springmvcapp.entity.Elector;
import com.step.springmvcapp.entity.User;
import com.step.springmvcapp.service.VoitingServiceForAdmin;
import com.step.springmvcapp.service.VoitingServiceForUser;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller 
@SessionAttributes("user")
@ComponentScan(basePackages = "com.step.springmvcapp")
public class AdminController {

    @Autowired
    VoitingServiceForAdmin adminService;
    @Autowired
    VoitingServiceForUser userService;
            
    private static final String REGISTRATION = "electors/registration";
    private static final String PROFILE = "electors/elector_profile";
    private static final String ADMIN= "users/admin";
    private static final String ELECTORS_LIST="electors/electors_list";
    private static final String CANDIDATE_FORM="candidates/editPage";
    private static final String CANDIDATES_LIST = "candidates/candidates_list";
    private static final String CANDIDATES_DETAILS_ADD="candidates/editPageForDetails";
    private static final String CANDIDATES_DETAILS = "candidates/details";
    
    @GetMapping(value = "/Electors")
    public String showElectors(@ModelAttribute("user") User user, ModelMap model ) {
        List <Elector> electors = userService.findElectors();
        if (electors.isEmpty()) {
            model.addAttribute("message", "The list of electors is empty");
            return ADMIN;
        }
        model.addAttribute("electors_list",  electors);
        return ELECTORS_LIST;
    }
    
    @GetMapping(value = "/Electors/edit/{id}")
    public ModelAndView editPageForElector(@ModelAttribute Elector elector,
            @PathVariable("id") Long id, @ModelAttribute("message") String message, HttpSession httpSession ) {
        Elector electorInDB = userService.findElectorById(id);
        ModelAndView modelAndView = new ModelAndView();
        
        User user =(User)httpSession.getAttribute("user");
        String userPass = user.getPassword();
        String userHashPass = userService.hashPassword(userPass);
        User admin = adminService.getAdmin(1L);

        if (user.getLogin().equals(admin.getLogin())&
            userHashPass.equals(admin.getPassword())) {
            modelAndView.addObject("elector", electorInDB);
            modelAndView.setViewName(REGISTRATION);
            return modelAndView;
        }
        if (electorInDB.isVoted()==true){
            modelAndView.addObject("novoting", "You can't change your data after you've voted");
            modelAndView.addObject("electorProfile", electorInDB);
            modelAndView.setViewName(PROFILE);
        } else {
            //To show non-hash password to an elector
            electorInDB.setPassword(userPass);
            modelAndView.addObject("elector", electorInDB);
            modelAndView.setViewName(REGISTRATION);
        }
        return modelAndView;
    }

    @PostMapping (value = "/Electors/edit/{id}")
    public String editElector(@Valid Elector elector, 
            BindingResult result, ModelMap model, HttpSession httpSession,
            @PathVariable("id") Long id) {
        if (result.hasErrors()) {
            return REGISTRATION;
        }         
        User user =(User)httpSession.getAttribute("user");
        User admin = adminService.getAdmin(1L);
        //to hash user password
        String userPass = user.getPassword();
        String userHashPass = userService.hashPassword(userPass);
        
        List <Elector> electors = userService.findElectors();      
        if (user.getLogin().equals(admin.getLogin())&
            userHashPass.equals(admin.getPassword())) {  
            if (!this.userService.findElectorById(id).getPassword().equals(elector.getPassword())){
                model.addAttribute("electors_list",  electors);
                model.addAttribute("message","The administrator is not allowed to change the password!");
                return ELECTORS_LIST;
            }
            Elector electorEdit = this.userService.findElectorById(id);
            Candidate candidate = electorEdit.getCandidate();
            boolean vote = electorEdit.isVoted();
            elector.setCandidate(candidate);
            elector.setVoted(vote); 
            this.adminService.saveElector(elector);
            model.addAttribute("message","The data has been successfully changed!");
            return "redirect:/Electors/";
        } else {
            //to hash changed password
            if (!this.userService.findElectorById(id).getPassword().equals(elector.getPassword())){
                String hashPass = this.userService.hashPassword(elector.getPassword());
                elector.setPassword(hashPass);
            }
            this.adminService.saveElector(elector);
            model.addAttribute("electorProfile", elector);
        }
        return PROFILE;
    }   
    
    @PostMapping(value = "/Electors/delete/{id}")
    public String deleteElector(@PathVariable("id") Long id, ModelMap model) {//@ModelAttribute("message") String message,
        Elector elector = userService.findElectorById(id);
        adminService.deleteElectorById(id);
        model.addAttribute("message", elector.getFirstName() +" "+elector.getLastName()+ " has been deleted");
        return "redirect:/Electors/";
    }

    @GetMapping(value = "/Candidates/edit/{id}")
    public String editPageForCandidate(@ModelAttribute Candidate candidate, 
            ModelMap model,@PathVariable("id") Long id,HttpSession httpSession ) { 
        Candidate candidateInBD = userService.findCandidateById(id);

        User user =(User)httpSession.getAttribute("user");
        User admin = adminService.getAdmin(1L);
        String userPass = user.getPassword();
        String userHashPass = userService.hashPassword(userPass);
        
        List<Candidate> candidates = userService.findCandidates();
        if (user.getLogin().equals(admin.getLogin())&
            userHashPass.equals(admin.getPassword())) {
            model.addAttribute("candidate", candidateInBD);
            return CANDIDATE_FORM;
        } 
        model.addAttribute("message", "The feature EDIT is available for only  administrator");
        model.addAttribute("candidates", candidates);
        //modelAndView.setViewName("redirect:/Candidates/");           
        return CANDIDATES_LIST;
    }
    
    @PostMapping (value = "/Candidates/edit/{id}")
    public String editCandidate(@ModelAttribute("candidate")@Valid Candidate candidate, BindingResult result,
            ModelMap model,@PathVariable("id") Long id) {     
        if (result.hasErrors()) {
            return CANDIDATE_FORM;
        }  
//        CandidateDetails details= userService.findCandidateDetailsById(id);//candidate.getDetails();
//        details.setCandidate(candidate);
//        //details.setId(id);
//        this.adminService.saveCandidateDetails(details);
//        candidate.setDetails(details);        
//        this.adminService.saveCandidate(candidate); 
//        return "redirect:/Candidates/";
        
        //CandidateDetails details= userService.findCandidateDetailsById(id);//candidate.getDetails();
        CandidateDetails details= userService.findCandidateById(id).getDetails();
        if (details==null){
           details = new CandidateDetails();
           details.setId(id);
        }
        details.setCandidate(candidate);
        //details.setId(id);
        this.adminService.saveCandidateDetails(details);
        candidate.setId(id);
        candidate.setDetails(details);   
        this.adminService.saveCandidate(candidate); 
        model.addAttribute("message","Data has been successfully changed");
        return "redirect:/Candidates/";

    } 
    
    @PostMapping(value = "/Candidates/delete/{id}")
    public String deleteCandidate(@PathVariable("id") Long id, ModelMap model,HttpSession httpSession ) {//@ModelAttribute("message") String message,
        Candidate candidate = userService.findCandidateById(id);
        User user =(User)httpSession.getAttribute("user");
        User admin = adminService.getAdmin(1L);
        String userPass = user.getPassword();
        String userHashPass = userService.hashPassword(userPass);
        List<Candidate> candidates = userService.findCandidates();
        if (user.getLogin().equals(admin.getLogin())&
            userHashPass.equals(admin.getPassword())) {
            adminService.deleteCandidateById(id);
            model.addAttribute("message", candidate.getFirstName() +" "+candidate.getLastName()+ " has been deleted");
        } else {
            model.addAttribute("candidates", candidates);
            model.addAttribute("message", "The feature DELETE is available for only  administrator");
            return CANDIDATES_LIST;
        }       
        return "redirect:/Candidates/";
    }
    
    @GetMapping(value="/CandidateDetails/edit/{id}")
    public ModelAndView editPageForCandidateDetails(@ModelAttribute Candidate details,
            @PathVariable("id") Long id, HttpSession httpSession ) {    
        ModelAndView modelAndView = new ModelAndView();
        CandidateDetails detailsInBD = userService.findCandidateDetailsById(id);
        Candidate candidate = userService.findCandidateById(id);
        User user =(User)httpSession.getAttribute("user");
        User admin = adminService.getAdmin(1L);
        String userPass = user.getPassword();
        String userHashPass = userService.hashPassword(userPass);
        if (user.getLogin().equals(admin.getLogin())&
            userHashPass.equals(admin.getPassword())) { 
            modelAndView.addObject("details", detailsInBD);
            modelAndView.setViewName(CANDIDATES_DETAILS_ADD);
        } 
        else {
            modelAndView.addObject("candidate", candidate);
            modelAndView.addObject("details", detailsInBD);
            modelAndView.addObject("message", "The feature EDIT is available for only  administrator");
            modelAndView.setViewName(CANDIDATES_DETAILS);
            // modelAndView.setViewName("redirect:/Candidates/"+id);
        }
        return modelAndView;
    }
  
    @PostMapping (value="/CandidateDetails/edit/{id}")
    public ModelAndView editCandidateDetails(@ModelAttribute("details") @Valid CandidateDetails details, 
            ModelMap model,BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("redirect:/Candidates/"+details.getId());
        }   
        this.adminService.saveCandidateDetails(details);
        modelAndView.addObject("message","Data has been successfully changed");
        modelAndView.setViewName("redirect:/Candidates/"+details.getId());
        return modelAndView;
    }
    
    @GetMapping(value = "/Candidates/add")
    public String addCandidate(@ModelAttribute Candidate candidate, ModelMap model, HttpSession httpSession ) {
        Candidate addCandidate = new Candidate();
        User user =(User)httpSession.getAttribute("user");
        User admin = adminService.getAdmin(1L);
        String userPass = user.getPassword();
        String userHashPass = userService.hashPassword(userPass);
        if (user.getLogin().equals(admin.getLogin())&
            userHashPass.equals(admin.getPassword())) {
            return CANDIDATE_FORM;
        }
        List<Candidate> candidates = userService.findCandidates();
        model.addAttribute("message", "The feature ADD is available for only  administrator");
        model.addAttribute("candidates", candidates);
        return CANDIDATES_LIST;
    }

    @PostMapping(value = "/Candidates/add")
    public String saveCandidate(@Valid Candidate candidate,BindingResult result, 
                                    ModelMap model) {
        if (result.hasErrors()) {
            return CANDIDATE_FORM;
        }
        List<Candidate> candidates = userService.findCandidates();
        if (!userService.findCandidates().contains(candidate)) {
            this.adminService.saveCandidate(candidate);
        } else {
            model.addAttribute("failed_registration", "Such candidate is registered in the system");
        }
        return "redirect:/Candidates/";
    }
}
