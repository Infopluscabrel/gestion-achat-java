package com.gestionachat4.controller;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gestionachat4.entities.Acheter;
import com.gestionachat4.entities.Client;
import com.gestionachat4.entities.MyRequest;
import com.gestionachat4.entities.Produit;
import com.gestionachat4.repository.ClientRepository;


@RestController
@CrossOrigin("*")
@RequestMapping(value = "/client")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository ; 
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@RequestMapping(
			method= RequestMethod.POST , 
			value="/createclient" , 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE			
			)  
		public ResponseEntity<?> createClient(@RequestBody Client client) throws Exception {
		
		clientRepository.save(client)	 ;
		return new ResponseEntity<>(client , HttpStatus.OK) ;
		}
	
	@RequestMapping(
			method= RequestMethod.GET , 
			value="/all" , 
			produces = MediaType.APPLICATION_JSON_VALUE
			//consumes = MediaType.APPLICATION_JSON_VALUE			
			)  
		public ResponseEntity<?> allClient() throws Exception {
		
		 
		return new ResponseEntity<>(clientRepository.findAll()	 , HttpStatus.OK) ;
		}
	
	/*@RequestMapping(
			method= RequestMethod.POST , 
			value="/update" , 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE			
			)  
		public <T> ResponseEntity<?> updateClient(@RequestBody Client client) throws Exception {
		
		System.out.println(client.toString());
	Client cl = clientRepository.updateById(client.getCodeclient())	 ;
	 if (cl==null) {
		// Object res= new Object( ) {message :" Client introuvable "}  ;
		// res.
		 //{message :" Client introuvable "} ; 
		 return new ResponseEntity<T>(HttpStatus.NOT_FOUND) ;
	 } 
	
	clientRepository.save(client);
	
	System.out.println(client.toString());
	
		return new ResponseEntity<>(client , HttpStatus.OK) ;
		}
*/

//delete by id 
@RequestMapping(
		method= RequestMethod.GET , 
		value="/delete/{id}" 
		//produces = MediaType.APPLICATION_JSON_VALUE,
		//consumes = MediaType.APPLICATION_JSON_VALUE			
		)  
	public <T> ResponseEntity<?> deleteClient(@PathVariable("id")  Client client) throws Exception {
	
	System.out.println(client.toString());
	
	
	if (clientRepository.findById(client.getCodeclient() ) ==null ) {
		 return new ResponseEntity<T>(HttpStatus.NOT_FOUND) ;
	}

 clientRepository.deleteById(client.getCodeclient());
	
// Client cl = clientRepository.deteById(client.getCodeclient())	 ;
 
	return new ResponseEntity<>( HttpStatus.OK) ;
	}


@RequestMapping(
		method= RequestMethod.GET , 
		value="/allproduit/{id}" 
		//produces = MediaType.APPLICATION_JSON_VALUE 
		//consumes = MediaType.APPLICATION_JSON_VALUE			
		)  
	public ResponseEntity<?> allClientProduit(@PathVariable("id") String code) throws Exception {
	

	
	
	//liste des achats d'un client 
	
	//String code =Short.toString(codeclient);
	
	// premiere methode 
	
	/*
	TypedQuery<Produit> query
    = entityManager.createQuery(
        "SELECT d FROM Produit p, AcheterPK a INNER JOIN p.acheterList where  a.codeclient=:id  ", Produit.class);
	query.setParameter(1,code) ;
	
	 System.out.println(query.getResultList().get(1) + " " + query.getResultList().get(2));
	
	return new ResponseEntity<>(query.getResultList()	 , HttpStatus.OK) ;
	}
*/
	
	
	/* SELECT produit.codeproduit ,produit.label , produit.pu , acheter.qte 
    			 From produit , acheter , client 
    			 where   acheter.codeproduit=produit.codeproduit 
    			 And acheter.codeclient= client.codeclient 
    			 AND  client.codeclient=59 */ 
	
	// deuxieme methode native query
	//System.out.println(" code : " + code);
	
	Query query = entityManager.
    createNativeQuery( "SELECT produit.codeproduit as codeproduit ,produit.label as label , produit.pu as pu, acheter.qte as qte"
    				+ " From produit , acheter , client "
    				+ " where   acheter.codeproduit=produit.codeproduit "
    				+ " And acheter.codeclient= client.codeclient "
    				+ " AND  client.codeclient=?1 ");
	query.setParameter(1,code) ;
	

	return new ResponseEntity<Object>(  query.getResultList() , HttpStatus.OK) ;
	}

@RequestMapping(
		method= RequestMethod.GET , 
		value="/allproduit2/{id}" 
		//produces = MediaType.APPLICATION_JSON_VALUE 
		//consumes = MediaType.APPLICATION_JSON_VALUE			
		)  
	public ResponseEntity<?> allClientProduit2(@PathVariable("id") String code) throws Exception {
	

	
	
	//liste des achats d'un client 
	
	//String code =Short.toString(codeclient);
	
	// premiere methode 
	
	/*
	TypedQuery<Produit> query
    = entityManager.createQuery(
        "SELECT d FROM Produit p, AcheterPK a INNER JOIN p.acheterList where  a.codeclient=:id  ", Produit.class);
	query.setParameter(1,code) ;
	
	 System.out.println(query.getResultList().get(1) + " " + query.getResultList().get(2));
	
	return new ResponseEntity<>(query.getResultList()	 , HttpStatus.OK) ;
	}
*/
	
	
	/* SELECT produit.codeproduit ,produit.label , produit.pu , acheter.qte 
    			 From produit , acheter , client 
    			 where   acheter.codeproduit=produit.codeproduit 
    			 And acheter.codeclient= client.codeclient 
    			 AND  client.codeclient=59 */ 
	
	// deuxieme methode native query
	//System.out.println(" code : " + code);
	
	Query query = entityManager.
    createNativeQuery( "SELECT acheter.* ,  produit.codeproduit as codeproduit ,produit.label as label , produit.pu as pu, acheter.qte as qte"
    				+ " From produit , acheter , client "
    				+ " where   acheter.codeproduit=produit.codeproduit "
    				+ " And acheter.codeclient= client.codeclient "
    				+ " AND  client.codeclient=?1 " , Acheter.class);
	query.setParameter(1,code) ;
	

	return new ResponseEntity<Object>(  query.getResultList() , HttpStatus.OK) ;
	}

@RequestMapping(
		method= RequestMethod.GET , 
		value="/allproduit3/{id}" 
		//produces = MediaType.APPLICATION_JSON_VALUE 
		//consumes = MediaType.APPLICATION_JSON_VALUE			
		)  
	public ResponseEntity<?> allClientProduit3(@PathVariable("id") String code) throws Exception {
	


		Query query = entityManager.createNativeQuery(
		    "SELECT produit.codeproduit as codeproduit ,produit.label as label , produit.pu as pu, acheter.qte as qte"
		    + " From produit , acheter , client "
		    + " where   acheter.codeproduit=produit.codeproduit "
		    + " And acheter.codeclient= client.codeclient "
		    + " AND  client.codeclient=?1 "
		);
		query.setParameter(1, code);

		

		List<Object[]> results = query.getResultList();

		List<MyRequest> produitsAchetes = results.stream()
		    .map(row -> new MyRequest((short)row[0], (String)row[1], (BigDecimal)row[2], (int)row[3]))
		    .collect(Collectors.toList());
		
			return new ResponseEntity<Object>( produitsAchetes , HttpStatus.OK) ;
			}

}


