package id.my.hilmiat.sping_h2.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import id.my.hilmiat.sping_h2.exception.PersonNotFoundException;
import id.my.hilmiat.sping_h2.model.Person;
import id.my.hilmiat.sping_h2.repository.PersonModelAssembler;
import id.my.hilmiat.sping_h2.repository.PersonRepository;
import id.my.hilmiat.sping_h2.repository.PersonService;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    private final PersonRepository personRepository;
    private final PersonService service;
    private final PersonModelAssembler assembler;

	public PersonController(PersonRepository repo, PersonService service, PersonModelAssembler assembler){
		this.personRepository = repo;
        this.service = service;
        this.assembler = assembler;
	}

    @GetMapping("/name")
    public List<String> getPersonName(){
        return this.personRepository.getName();
    }


	@GetMapping("/cari")
	public Page<Person> getPersons(
        @RequestParam(defaultValue = "",name = "q") String q,
        @RequestParam(defaultValue = "0") int pageNumber,
        @RequestParam(defaultValue = "10") int pageSize
        ){
        PageRequest paging = PageRequest.of(pageNumber, pageSize);
		return this.service.customQuery(q,paging);
	}
    @GetMapping
	public CollectionModel<EntityModel<Person>> getPersons(
        @RequestParam(defaultValue = "0") int pageNumber,
        @RequestParam(defaultValue = "10") int pageSize,
        @RequestParam(defaultValue = "id,desc") String[] sort
    ){
        Sort sorting = Sort.by(sort[0]).ascending();
        // return this.personRepository.findAll();
        // filter nilai dari pageNumber dan pageSize agar > 0
        if(pageNumber<0 || pageSize<0){
            //tidak valid
        }

        PageRequest paging = PageRequest.of(pageNumber, pageSize, sorting);
        // return this.personRepository.findAll(paging);
        
        List<EntityModel<Person>> data =  this.personRepository.findAll(paging).stream()
            .map(assembler::toModel).collect(Collectors.toList());

        Link self = linkTo(methodOn(PersonController.class).getPersons(pageNumber,pageSize,sort))
        .withSelfRel();
        return CollectionModel.of(data,self);
        
	}

	@PostMapping
	public Person addPerson(@RequestBody Person p){
		return this.personRepository.save(p);
	}

    @GetMapping("/{id}")
    public EntityModel<Person> getById(@PathVariable Long id){

        Person person = this.personRepository
        .findById(id)
        .orElseThrow(()-> new PersonNotFoundException(id));

        // // Link self = Link.of("test");

        // Link self = linkTo(methodOn(PersonController.class)
        //     .getById(id))
        //     .withSelfRel();
        // Link all = linkTo(methodOn(PersonController.class)
        //     .getPersons(0, 10,null))
        //     .withRel("persons");

        return assembler.toModel(person);
        

       
    }

    @PutMapping("/{id}")
    public Person updatePerson(@RequestBody Person p, @PathVariable Long id){
        p.setId(id);
        return this.personRepository.save(p);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id){
        this.personRepository.deleteById(id);
    }


    private static String FOLDER_UPLOAD = "src/main/resources/images";

    @PostMapping("/upload")
    public String fileUpload(
        @RequestParam(name = "file") MultipartFile file
    ){
        if(file.isEmpty()){
            ResponseEntity.badRequest();
        }
        try{
            byte[] dataByte = file.getBytes();
            Path dir = Paths.get(FOLDER_UPLOAD);
            Path lokasi = Paths.get(FOLDER_UPLOAD,file.getOriginalFilename());
            //jika folder belum ada
            if(!Files.exists(dir)){
                Files.createDirectories(dir);
            }
            //simpan file
            Files.write(lokasi, dataByte);
            return "Upload sukses";
        }catch(IOException e){
            e.printStackTrace();
            return "Gagal upload file";
        }
    }

    @GetMapping("/image")
    public ResponseEntity<byte[]> getImages(){
         ClassPathResource imgFile = new ClassPathResource("images/test.jpg");
        byte[] data;
		try {
			data = StreamUtils.copyToByteArray(imgFile.getInputStream());
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(data);
		} catch (IOException e) {
			return (ResponseEntity<byte[]>) ResponseEntity.notFound();
		}
    }

    @PostMapping("/{id}/upload")
    public String uploadImagePerson(
        @RequestParam(name = "file") MultipartFile file,
        @PathVariable Long id
    ){
        if(file.isEmpty()){
            ResponseEntity.badRequest();
        }
        try{
            byte[] dataByte = file.getBytes();
            Path dir = Paths.get(FOLDER_UPLOAD);
            Path lokasi = Paths.get(FOLDER_UPLOAD,id+".jpg");
            //jika folder belum ada
            if(!Files.exists(dir)){
                Files.createDirectories(dir);
            }
            //simpan file
            Files.write(lokasi, dataByte);
            return "Upload sukses di "+lokasi.toString();
        }catch(IOException e){
            e.printStackTrace();
            return "Gagal upload file";
        }

    }
    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getImages(@PathVariable Long id){
         ClassPathResource imgFile = new ClassPathResource("images/"+id+".jpg");
        byte[] data;
		try {
			data = StreamUtils.copyToByteArray(imgFile.getInputStream());
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(data);
		} catch (IOException e) {
			return (ResponseEntity<byte[]>) ResponseEntity.notFound();
		}
    }   
    
}
