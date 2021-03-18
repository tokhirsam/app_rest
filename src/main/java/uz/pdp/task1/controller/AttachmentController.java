package uz.pdp.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.task1.entity.Attachment;
import uz.pdp.task1.payload.Result;
import uz.pdp.task1.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/attachment")
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;

    @PostMapping("/upload")
    public Result upload(MultipartHttpServletRequest request){
        return attachmentService.uploadFile(request);
    }

    @GetMapping("/info")
    public List<Attachment> getInfoOfFiles(){
        return attachmentService.getInfoOfFiles();
    }
    @GetMapping("/info/{id}")
    public Attachment getInfoOfFiles(@PathVariable Integer id){
        return attachmentService.getInfoOfFile(id);
    }
    @GetMapping("/download/{id}")
    public void getFileFromDb(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        attachmentService.getFileFromDb(id, response);
    }
}
