## 📡 LiveKit 소개
LiveKit이란?

WebRTC 기반 오픈소스 실시간 통신 플랫폼

SFU(Selective Forwarding Unit) 아키텍처를 사용하여 오디오, 비디오, 화면 공유를 저지연으로 중계

자체 서버 배포 가능 + 다양한 SDK(웹, 모바일, Unity 등) 제공

동작 방식
[브라우저 A] --(카메라/마이크 트랙)--> [ LiveKit 서버(SFU) ] --> [브라우저 B]
▲                                                           ▼
└------------------- 구독(Subcribe) ------------------------┘


Client: 카메라·마이크·화면공유 같은 Track을 Publish

LiveKit Server(SFU): 받은 트랙을 선택적으로 다른 참가자에게 Forward

다른 Client: 필요한 트랙만 Subscribe 해서 수신

## 핵심 개념

Room: 참가자들이 모이는 공간

Participant: 방에 들어온 사용자 (Local/Remote)

Track: 오디오/비디오/데이터 단위 (ex. 마이크 오디오, 화면 공유 비디오)

Publisher/Subscriber: 트랙을 올리는 쪽 / 받는 쪽

DataTrack: 소량의 데이터(상태, 시그널 등) 교환 채널

## 장점

✅ 확장성: 참가자가 많아도 업로드는 항상 1회 → 서버가 중계

✅ 낮은 지연: WebRTC 기반, 밀리초 단위 실시간

✅ 부가기능: 녹화(Egress), 방송(RTMP Out), 권한(Role) 제어, Simulcast

✅ 오픈소스: 자유로운 배포 및 커스터마이징 가능

## 활용 예시

화상 회의 서비스 (Zoom, Meet와 유사)

온라인 강의/튜터링 플랫폼

게임/메타버스 내 실시간 음성 채팅

RTMP 방송 연동(YouTube/Twitch)

원격 협업 툴 (화면 공유 + 음성 회의 + 채팅)

## 한 줄 요약

“LiveKit은 실시간 오디오·비디오 트랙을 SFU 서버로 중계해, 대규모 저지연 커뮤니케이션을 가능하게 하는 WebRTC 기반 오픈소스 플랫폼이다.”