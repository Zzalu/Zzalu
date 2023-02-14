package com.samsamoo.zzalu.chat.service;

import com.samsamoo.zzalu.chat.entity.ChatRoom;
import com.samsamoo.zzalu.chat.repository.ChatRoomRepository;
import com.samsamoo.zzalu.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    public Optional<ChatRoom> findByRoomId(String roomId){
        return chatRoomRepository.findByRoomId(roomId);
    }
    public List<ChatRoom> findTop10ByOrderByLikeCountDesc() {
        return chatRoomRepository.findTop10ByOrderByLikeCountDesc();
    }

    public List<ChatRoom> findByTagsContainsOrRoomNameContains(String keyword) {
        return chatRoomRepository.findByTagsContainsOrRoomNameContains(keyword, keyword);
    }

    public List<ChatRoom> findByTagsContains(String keyword) {
        return chatRoomRepository.findByTagsContains(keyword);
    }

    public List<ChatRoom> findByRoomNameContains(String keyword) {
        return chatRoomRepository.findByRoomNameContains(keyword);
    }

    public List<ChatRoom> findAll(){
        return chatRoomRepository.findAll();
    }

    public List<ChatRoom> findAllByMemberId(Long memberId){
        return chatRoomRepository.findAllByMemberId(memberId);
    }
    public List<ChatRoom> findAllByMemberIdOrderByLastActivationDesc(Long memberId){
        return chatRoomRepository.findAllByMemberIdOrderByLastActivationDesc(memberId);
    }
    public List<ChatRoom> findAllByMemberIdOrderByLikeCountDesc(Long memberId){
        return chatRoomRepository.findAllByMemberIdOrderByLikeCountDesc(memberId);
    }
    public List<ChatRoom> findAllByTagsContainsOrRoomNameContainsOrderByLastActivationDesc(String keyword1, String keyword2){
        return chatRoomRepository.findAllByTagsContainsOrRoomNameContainsOrderByLastActivationDesc(keyword1, keyword2);
    }

    public List<ChatRoom> findAllByTagsContainsOrRoomNameContainsOrderByLikeCount(String keyword1, String keyword2){
        return chatRoomRepository.findAllByTagsContainsOrRoomNameContainsOrderByLikeCount(keyword1, keyword2);
    }

    public List<ChatRoom> findAllByMemberIdAndTagsContainsOrRoomNameContainsOrderByLastActivationDesc(Long memberId, String keyword1, String keyword2){
        return chatRoomRepository.findAllByMemberIdAndTagsContainsOrRoomNameContainsOrderByLastActivationDesc(memberId, keyword1, keyword2);
    }

    public List<ChatRoom> findAllByMemberIdAndTagsContainsOrRoomNameContainsOrderByLikeCountDesc(Long memberId, String keyword1, String keyword2){
        return chatRoomRepository.findAllByMemberIdAndTagsContainsOrRoomNameContainsOrderByLikeCountDesc(memberId, keyword1, keyword2);
    }


    public void save(ChatRoom chatRoom) {
        chatRoomRepository.save(chatRoom);
    }

    public List<ChatRoom> findAllByLikeMembersContainsAndTagsContainsOrRoomNameContainsOrderByLastActivationDesc(Member member, String keyword1, String keyword2) {
        return chatRoomRepository.findAllByLikeMembersContainsAndTagsContainsOrRoomNameContainsOrderByLastActivationDesc(member, keyword1, keyword2);
    }

    public List<ChatRoom> findAllByLikeMembersAndTagsContainsOrRoomNameContainsOrderByLikeCountDesc(Member member, String keyword1, String keyword2) {
        return chatRoomRepository.findAllByLikeMembersAndTagsContainsOrRoomNameContainsOrderByLikeCountDesc(member, keyword1, keyword2);
    }
}