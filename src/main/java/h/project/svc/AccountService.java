//package h.project.svc;
//
//import java.util.Optional;
//
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import h.project.dao.AccountDao;
//import h.project.dto.AccountDto;
//import h.project.repo.AccountRepository;
//import lombok.RequiredArgsConstructor;
//
//@Service
//@Transactional(readOnly = true)
//@RequiredArgsConstructor
//public class AccountService {
//
//	    private final AccountRepository accountRepository;
//
//	    @Transactional
//	    public String createUser(AccountDto accountDto) {
//	        AccountDao account = accountDto.toEntity();
//	        Optional<AccountDao> validateUserId = accountRepository.findById(account.getEmail());
//	        if(!validateUserId.isPresent()) {
//	        	 accountRepository.save(account);
//	        	 //redis
//	        	 // 레디스저장할 로직 추가
//	        	 
//	        } else {
//	        	String result = "data";
//	        	return result;
//	        }
//	       
//	        return account.getEmail();
//	        
//	    }
//
//	    
//}
