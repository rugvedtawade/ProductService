package com.scaler.productservice.services;
import java.util.ArrayList;
import java.util.List;
import com.scaler.productservice.dtos.CreateProductRequestDTO;
import com.scaler.productservice.dtos.FakeStoreProductDto;
import com.scaler.productservice.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService
{
    private RestTemplate restTemplate; // way to contact the internet and get data

    public  FakeStoreProductService(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(long id) {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDTOResponse = restTemplate.getForEntity("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        return fakeStoreProductDTOResponse.getBody().toProduct();
    }

    @Override
    public List<Product> getAllProducts()
    {
        FakeStoreProductDto[] fakeStoreProductDtoArray = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtoArray)
        {
            products.add(fakeStoreProductDto.toProduct());
        }
        return products;
    }

//    @Override
//    public Product createProduct(CreateProductRequestDTO createProductRequestDTO) {   // Method in case of DTO object sent by Controller
//        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
//        fakeStoreProductDto.setTitle(createProductRequestDTO.getTitle());
//        fakeStoreProductDto.setDescription(createProductRequestDTO.getDescription());
//        fakeStoreProductDto.setPrice(createProductRequestDTO.getPrice());
//        fakeStoreProductDto.setImage(createProductRequestDTO.getImage());
//        fakeStoreProductDto.setCategory(createProductRequestDTO.getCategory());
//
//        FakeStoreProductDto fakeStoreProductDto1 = restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreProductDto, FakeStoreProductDto.class);
//        return fakeStoreProductDto1.toProduct(); // for testing purpouse
//    }

    @Override
    public Product createProduct(String title, String description, String image, String category, double price) {
        return null;
    }
}
