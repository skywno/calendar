INSERT INTO `user` (created_at, modified_at, birthday, email, name, password) VALUES (NOW(), NOW(), '1996-06-16', 'test2@test.com', 'test_user1', 'test');

INSERT INTO schedule (title, description, schedule_type, start_at, end_at, writer_id, created_at, modified_at) VALUES ('여행1', '제주도', 'EVENT', '2022-11-25 00:00:00', '2022-11-28 14:00:00', 1, NOW(), NOW());
INSERT INTO schedule (title, description, schedule_type, start_at, end_at, writer_id, created_at, modified_at) VALUES ('여행2', '거제도', 'EVENT', '2022-12-25 00:00:00', '2022-12-28 14:00:00', 1, NOW(), NOW());
INSERT INTO schedule (title, description, schedule_type, start_at, end_at, writer_id, created_at, modified_at) VALUES ('여행3', '울등도', 'EVENT', '2023-01-25 00:00:00', '2023-01-28 14:00:00', 1, NOW(), NOW());
INSERT INTO schedule (title, description, schedule_type, start_at, end_at, writer_id, created_at, modified_at) VALUES ('여행4', '강화도', 'EVENT', '2023-02-25 00:00:00', '2023-02-28 14:00:00', 1, NOW(), NOW());
INSERT INTO schedule (title, description, schedule_type, start_at, end_at, writer_id, created_at, modified_at) VALUES ('운동1', '가슴', 'TASK', '2022-11-25 00:00:00', NULL, 1, NOW(), NOW());
INSERT INTO schedule (title, description, schedule_type, start_at, end_at, writer_id, created_at, modified_at) VALUES ('운동2', '허리', 'TASK', '2022-11-26 00:00:00', NULL, 1, NOW(), NOW());
INSERT INTO schedule (title, description, schedule_type, start_at, end_at, writer_id, created_at, modified_at) VALUES ('운동3', '운동', 'TASK', '2022-11-27 00:00:00', NULL, 1, NOW(), NOW());
INSERT INTO schedule (title, description, schedule_type, start_at, end_at, writer_id, created_at, modified_at) VALUES ('운동4', '다리', 'TASK', '2022-11-28 00:00:00', NULL, 1, NOW(), NOW());
INSERT INTO schedule (title, description, schedule_type, start_at, end_at, writer_id, created_at, modified_at) VALUES ('알람1', NULL, 'NOTIFICATION', '2022-11-25 00:00:00', NULL, 1, NOW(), NOW());
INSERT INTO schedule (title, description, schedule_type, start_at, end_at, writer_id, created_at, modified_at) VALUES ('알람2', NULL, 'NOTIFICATION', '2022-11-26 00:00:00', NULL, 1, NOW(), NOW());
INSERT INTO schedule (title, description, schedule_type, start_at, end_at, writer_id, created_at, modified_at) VALUES ('알람3', NULL, 'NOTIFICATION', '2022-11-27 00:00:00', NULL, 1, NOW(), NOW());
INSERT INTO schedule (title, description, schedule_type, start_at, end_at, writer_id, created_at, modified_at) VALUES ('알람4', NULL, 'NOTIFICATION', '2022-11-28 00:00:00', NULL, 1, NOW(), NOW());
INSERT INTO schedule (title, description, schedule_type, start_at, end_at, writer_id, created_at, modified_at) VALUES ('알람5', NULL, 'NOTIFICATION', '2022-11-29 00:00:00', NULL, 1, NOW(), NOW());

INSERT INTO invitation(created_at, modified_at, request_status, participant_id, schedule_id) VALUES (NOW(), NOW(), 'ACCEPTED', 1, 1 );
INSERT INTO invitation(created_at, modified_at, request_status, participant_id, schedule_id) VALUES (NOW(), NOW(), 'ACCEPTED', 1, 2 );
INSERT INTO invitation(created_at, modified_at, request_status, participant_id, schedule_id) VALUES (NOW(), NOW(), 'ACCEPTED', 1, 3 );
INSERT INTO invitation(created_at, modified_at, request_status, participant_id, schedule_id) VALUES (NOW(), NOW(), 'ACCEPTED', 1, 4 );
INSERT INTO invitation(created_at, modified_at, request_status, participant_id, schedule_id) VALUES (NOW(), NOW(), 'ACCEPTED', 1, 5 );
INSERT INTO invitation(created_at, modified_at, request_status, participant_id, schedule_id) VALUES (NOW(), NOW(), 'ACCEPTED', 1, 6 );
INSERT INTO invitation(created_at, modified_at, request_status, participant_id, schedule_id) VALUES (NOW(), NOW(), 'ACCEPTED', 1, 7 );
INSERT INTO invitation(created_at, modified_at, request_status, participant_id, schedule_id) VALUES (NOW(), NOW(), 'ACCEPTED', 1, 8 );
INSERT INTO invitation(created_at, modified_at, request_status, participant_id, schedule_id) VALUES (NOW(), NOW(), 'ACCEPTED', 1, 9 );
INSERT INTO invitation(created_at, modified_at, request_status, participant_id, schedule_id) VALUES (NOW(), NOW(), 'ACCEPTED', 1, 10);
INSERT INTO invitation(created_at, modified_at, request_status, participant_id, schedule_id) VALUES (NOW(), NOW(), 'ACCEPTED', 1, 11);
INSERT INTO invitation(created_at, modified_at, request_status, participant_id, schedule_id) VALUES (NOW(), NOW(), 'ACCEPTED', 1, 12);
INSERT INTO invitation(created_at, modified_at, request_status, participant_id, schedule_id) VALUES (NOW(), NOW(), 'ACCEPTED', 1, 13);