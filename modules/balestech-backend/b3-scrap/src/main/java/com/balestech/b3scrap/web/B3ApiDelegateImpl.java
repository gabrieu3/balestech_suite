package com.balestech.b3scrap.web;



import com.balestech.b3web.model.dto.IbovCompaniesResponse;
import com.balestech.b3web.rest.B3ApiDelegate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class B3ApiDelegateImpl implements B3ApiDelegate {

    @Override
    public ResponseEntity<IbovCompaniesResponse> ibovCompaniesGet() {
        if(1 == 1)
            throw new RuntimeException("teste");
        return new ResponseEntity<IbovCompaniesResponse>(new IbovCompaniesResponse(), HttpStatus.OK);
    }

}
