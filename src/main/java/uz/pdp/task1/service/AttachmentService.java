package uz.pdp.task1.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.task1.entity.Attachment;
import uz.pdp.task1.entity.AttachmentContent;
import uz.pdp.task1.payload.Result;
import uz.pdp.task1.repository.AttachmentContentRepository;
import uz.pdp.task1.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;


    @SneakyThrows
    public Result uploadFile(MultipartHttpServletRequest request){
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment savedAttachment = attachmentRepository.save(attachment);


        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setBytes(file.getBytes());
        attachmentContent.setAttachment(savedAttachment);
        attachmentContentRepository.save(attachmentContent);
        return new Result("File saved", true,savedAttachment.getId());
    }
    public List<Attachment> getInfoOfFiles(){
        return attachmentRepository.findAll();
    }

    public Attachment getInfoOfFile(@PathVariable Integer id){
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        return optionalAttachment.orElseGet(Attachment::new);

    }

    public void getFileFromDb(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()){
            Attachment attachment = optionalAttachment.get();
            Optional<AttachmentContent> optionalContent = attachmentContentRepository.findByAttachmentId(id);
            if (optionalContent.isPresent()){
                AttachmentContent content = optionalContent.get();
                response.setHeader("Content-Disposition","attachment; filename=\""+attachment.getName()+"\"");
                response.setContentType(attachment.getContentType());
                FileCopyUtils.copy(content.getBytes(),response.getOutputStream());
            }
        }
    }
}
