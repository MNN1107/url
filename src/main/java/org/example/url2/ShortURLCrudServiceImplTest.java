package org.example.url2;

//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.List;
//import java.util.Optional;
//
//import static javax.management.Query.times;
//import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//public class ShortURLCrudServiceImplTest {
//
//    @Mock
//    private ShortURLRepository shortURLRepository;
//
//    @Mock
//    private ShortURLMapper shortURLMapper;
//
//    @Mock
//    private ShortURLGenerationService shortURLGenerationService;
//
//    private ShortURLCrudServiceImpl shortURLCrudService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        shortURLCrudService = new ShortURLCrudServiceImpl(shortURLRepository, shortURLMapper, shortURLGenerationService);
//    }
//
//    @Test
//    public void createShortURL_ShouldCreateAndReturnShortURLDTO() {
//        ShortURLDTO shortURLDTO = ShortURLDTO.builder()
//                .longURL("http://example.com")
//                .creator(new User())
//                .build();
//
//        ShortURL shortURL = new ShortURL();
//        ShortURL savedShortURL = new ShortURL();
//
//        when(URLValidator.isValid(anyString())).thenReturn(true);
//        when(URLValidator.isAccessibleUrl(anyString())).thenReturn(true);
//        when(shortURLGenerationService.generateShortURL(any(User.class))).thenReturn("abc123");
//        when(shortURLMapper.toEntity(any(ShortURLDTO.class))).thenReturn(shortURL);
//        when(shortURLRepository.save(any(ShortURL.class))).thenReturn(savedShortURL);
//        when(shortURLMapper.toDTO(any(ShortURL.class))).thenReturn(shortURLDTO);
//
//        ShortURLDTO result = shortURLCrudService.createShortURL(shortURLDTO);
//
//        assertNotNull(result);
//        assertEquals("abc123", result.getUrl());
//        verify(shortURLRepository, times(1)).save(any(ShortURL.class));
//    }
//
//    @Test
//    public void getShortURLById_ShouldReturnShortURLDTO() {
//        Long id = 1L;
//        ShortURL shortURL = new ShortURL();
//        ShortURLDTO shortURLDTO = new ShortURLDTO();
//
//        when(shortURLRepository.findById(id)).thenReturn(Optional.of(shortURL));
//        when(shortURLMapper.toDTO(shortURL)).thenReturn(shortURLDTO);
//
//        Optional<ShortURLDTO> result = shortURLCrudService.getShortURLById(id);
//
//        assertTrue(result.isPresent());
//        assertEquals(shortURLDTO, result.get());
//    }
//
//    @Test
//    public void getAllShortURLsByCreatorId_ShouldReturnListOfShortURLDTOs() {
//        Long userId = 1L;
//        List<ShortURL> shortURLList = List.of(new ShortURL(), new ShortURL());
//        List<ShortURLDTO> shortURLDTOList = List.of(new ShortURLDTO(), new ShortURLDTO());
//
//        when(shortURLRepository.findAllByCreatorId(userId)).thenReturn(shortURLList);
//        when(shortURLMapper.toDTO(any(ShortURL.class))).thenReturn(new ShortURLDTO());
//
//        List<ShortURLDTO> result = shortURLCrudService.getAllShortURLsByCreatorId(userId);
//
//        assertNotNull(result);
//        assertEquals(shortURLDTOList.size(), result.size());
//    }
//
//    @Test
//    public void updateShortURL_ShouldUpdateAndReturnShortURLDTO() {
//        ShortURLDTO shortURLDTO = new ShortURLDTO();
//        ShortURL shortURL = new ShortURL();
//
//        when(shortURLMapper.toEntity(any(ShortURLDTO.class))).thenReturn(shortURL);
//        when(shortURLRepository.save(any(ShortURL.class))).thenReturn(shortURL);
//        when(shortURLMapper.toDTO(any(ShortURL.class))).thenReturn(shortURLDTO);
//
//        ShortURLDTO result = shortURLCrudService.updateShortURL(shortURLDTO);
//
//        assertNotNull(result);
//        assertEquals(shortURLDTO, result);
//        verify(shortURLRepository, times(1)).save(any(ShortURL.class));
//    }
//
//    @Test
//    public void deleteShortURL_ShouldDeleteShortURL() {
//        Long id = 1L;
//
//        doNothing().when(shortURLRepository).deleteById(id);
//
//        shortURLCrudService.deleteShortURL(id);
//
//        verify(shortURLRepository, times(1)).deleteById(id);
//    }
//
//    @Test
//    public void redirectShortURL_ShouldReturnLongURLAndIncrementClickCount() {
//        String shortUrl = "abc123";
//        ShortURL shortURL = new ShortURL();
//        shortURL.setLongURL("http://example.com");
//        shortURL.setClickCount(0L);
//
//        when(shortURLRepository.findByUrl(shortUrl)).thenReturn(Optional.of(shortURL));
//        when(shortURLRepository.save(any(ShortURL.class))).thenReturn(shortURL);
//
//        String result = shortURLCrudService.redirectShortURL(shortUrl);
//
//        assertEquals("http://example.com", result);
//        assertEquals(1L, shortURL.getClickCount());
//        verify(shortURLRepository, times(1)).save(any(ShortURL.class));
//    }
//}
