package airbnb.infra;

import airbnb.config.kafka.KafkaProcessor;
import airbnb.domain.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ViewpageViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private ViewpageRepository viewpageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRoomRegistered_then_CREATE_1(
        @Payload RoomRegistered roomRegistered
    ) {
        try {
            if (!roomRegistered.validate()) return;

            // view 객체 생성
            Viewpage viewpage = new Viewpage();
            // view 객체에 이벤트의 Value 를 set 함
            viewpage.setId(roomRegistered.getId());
            viewpage.setName(roomRegistered.getName());
            viewpage.setDescription(roomRegistered.getDescription());
            viewpage.setPrice(roomRegistered.getPrice());
            viewpage.setReservationStatus(active);
            // view 레파지 토리에 save
            viewpageRepository.save(viewpage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRoomUpdated_then_UPDATE_1(
        @Payload RoomUpdated roomUpdated
    ) {
        try {
            if (!roomUpdated.validate()) return;
            // view 객체 조회
            Optional<Viewpage> viewpageOptional = viewpageRepository.findById(
                roomUpdated.getId()
            );

            if (viewpageOptional.isPresent()) {
                Viewpage viewpage = viewpageOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                viewpage.setName(roomUpdated.getName());
                viewpage.setDescription(roomUpdated.getDescription());
                viewpage.setPrice(roomUpdated.getPrice());
                // view 레파지 토리에 save
                viewpageRepository.save(viewpage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRoomDeleted_then_UPDATE_2(
        @Payload RoomDeleted roomDeleted
    ) {
        try {
            if (!roomDeleted.validate()) return;
            // view 객체 조회
            Optional<Viewpage> viewpageOptional = viewpageRepository.findById(
                roomDeleted.getId()
            );

            if (viewpageOptional.isPresent()) {
                Viewpage viewpage = viewpageOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                viewpage.setId(deleted);
                // view 레파지 토리에 save
                viewpageRepository.save(viewpage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRoomReserved_then_UPDATE_3(
        @Payload RoomReserved roomReserved
    ) {
        try {
            if (!roomReserved.validate()) return;
            // view 객체 조회
            Optional<Viewpage> viewpageOptional = viewpageRepository.findById(
                roomReserved.getRoomId()
            );

            if (viewpageOptional.isPresent()) {
                Viewpage viewpage = viewpageOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                viewpage.setReservationStatus(reserved);
                // view 레파지 토리에 save
                viewpageRepository.save(viewpage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRoomCancelled_then_UPDATE_4(
        @Payload RoomCancelled roomCancelled
    ) {
        try {
            if (!roomCancelled.validate()) return;
            // view 객체 조회
            Optional<Viewpage> viewpageOptional = viewpageRepository.findById(
                roomCancelled.getRoomId()
            );

            if (viewpageOptional.isPresent()) {
                Viewpage viewpage = viewpageOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                viewpage.setReservationStatus(cancelled);
                // view 레파지 토리에 save
                viewpageRepository.save(viewpage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
