package com.step.springmvcapp.service;

import com.step.springmvcapp.dao.AdminDAO;
import com.step.springmvcapp.dao.CandidateDAO;
import com.step.springmvcapp.dao.CandidateDetailsDAO;
import com.step.springmvcapp.dao.ElectorDAO;
import com.step.springmvcapp.entity.Candidate;
import com.step.springmvcapp.entity.CandidateDetails;
import com.step.springmvcapp.entity.Elector;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.List;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
//@Service
public class VoitingServiceForUserImpl implements VoitingServiceForUser {
    @Autowired
    private CandidateDAO candidateDao;
    @Autowired
    private ElectorDAO electorDao;
    @Autowired
    private CandidateDetailsDAO detailsDao;
    @Autowired
    private AdminDAO adminDao;
    @Autowired
    private VoitingServiceForAdmin  adminServ;// = new VoitingServiceForAdminImpl ();
    
    public VoitingServiceForUserImpl() {}

    public VoitingServiceForAdmin getServAdmin() {return adminServ;}

    public void setServAdmin(VoitingServiceForAdmin adminServ) {
        this.adminServ = adminServ;
    }

    @Override
    @Transactional(readOnly = true) // в 10 раз быстрее будет работать, только для чтения
    public List<Candidate> findCandidates() {
        return candidateDao.findAll();
    }

    @Override
    @Transactional(readOnly = true) // в 10 раз быстрее будет работать, только для чтения
    public Candidate findCandidateById(Long id) {
        return candidateDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true) // в 10 раз быстрее будет работать, только для чтения
    public List <Elector> findElectors() {
       return electorDao.findAll();
    }

    @Override
    @Transactional(readOnly = true) // в 10 раз быстрее будет работать, только для чтения
    public Elector findElectorById(Long id) {
        return electorDao.findById(id);
    }
  
    @Override
    public String hashPassword (String password ){
/*Принцип значения соли очень прост, то есть сначала объедините пароль и содержимое, указанное значением соли.
  Таким образом, даже если пароль является очень распространенной строкой и добавляется имя пользователя, 
  окончательно рассчитанное значение  не так легко угадать.
  Поскольку злоумышленник не знает значение соли, трудно восстановить исходный пароль.
*/
        String salt = "1234"; // 
        char[] passwordChars = password.toCharArray(); 
        byte[] saltBytes = salt.getBytes();
        String hashedString="";
        //StringBuilder builder = new StringBuilder();
         try {
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            //Длина хэша sha512 составляет 512 бит = 64 байта/символа
            KeySpec ks = new PBEKeySpec(passwordChars,saltBytes,10000,32);
            SecretKey key = f.generateSecret(ks);
            byte[] res = key.getEncoded();
            hashedString = Hex.encodeHexString(res);  
         } catch (NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException( e );
         }
        return hashedString;
     }

    @Override
    @Transactional
    public void vote (Long electorId, Long candidateId){
        Elector elector = findElectorById(electorId);
        Candidate candidate =  findCandidateById(candidateId);      
        Long voices = candidate.getVoices();
        if (voices==null)
           voices = 0L; 
        voices++;
        candidate.setVoices(voices);
        candidate.getElectors().add(elector);
        elector.setCandidate(candidate);
        elector.setVoted(true);
        adminServ.saveCandidate(candidate);
        adminServ.saveElector(elector);
    }
    
    @Transactional (readOnly = true)
    @Override
    public CandidateDetails findCandidateDetailsById(Long id){
         return detailsDao.findById(id);
    }

 
}
