
--
-- TOC entry 215 (class 1259 OID 16399)
-- Name: addresses; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.addresses (
    ismain boolean NOT NULL,
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    updatedat timestamp(6) without time zone,
    city character varying(255),
    country character varying(255),
    email character varying(255),
    firstname character varying(255),
    foto character varying(255),
    lastname character varying(255),
    phone character varying(255),
    postcode character varying(255),
    salutation character varying(255),
    signature character varying(255),
    street character varying(255),
    title character varying(255),
    type character varying(255),
    "`type`" character varying(255),
    picture character varying(255),
    secondarykey bigint
);


--
-- TOC entry 248 (class 1259 OID 16721)
-- Name: adult_interests; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.adult_interests (
    adult_id bigint NOT NULL,
    interests character varying(255)
);


--
-- TOC entry 249 (class 1259 OID 16743)
-- Name: adult_ocupation; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.adult_ocupation (
    adult_id bigint NOT NULL,
    ocupation character varying(255)
);


--
-- TOC entry 250 (class 1259 OID 16746)
-- Name: adult_personality; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.adult_personality (
    adult_id bigint NOT NULL,
    personality character varying(255)
);


--
-- TOC entry 216 (class 1259 OID 16406)
-- Name: adults; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.adults (
    birdthyear integer,
    carerid bigint,
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    tamagotchiid bigint,
    updatedat timestamp(6) without time zone,
    picture character varying(255),
    email character varying(255),
    firstname character varying(255),
    lastname character varying(255),
    phone character varying(255),
    preferredlanguage character varying(255),
    relationship character varying(255),
    salutation character varying(255),
    carer_id bigint,
    carer bigint,
    genre character varying(255),
    maritalstatus character varying(255),
    birdthdate date,
    gender character varying(255),
    details text,
    secondarykey bigint
);


--
-- TOC entry 217 (class 1259 OID 16413)
-- Name: auditlogs; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.auditlogs (
    createdat timestamp(6) without time zone,
    entityid bigint,
    id bigint NOT NULL,
    updatedat timestamp(6) without time zone,
    action character varying(255),
    details text,
    entity character varying(255),
    level character varying(255),
    username character varying(255),
    "`action`" character varying(255),
    "`level`" character varying(255),
    secondarykey bigint
);


--
-- TOC entry 218 (class 1259 OID 16420)
-- Name: chatlogs; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.chatlogs (
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    updatedat timestamp(6) without time zone,
    message text,
    sender character varying(255),
    secondarykey bigint
);


--
-- TOC entry 219 (class 1259 OID 16427)
-- Name: chatmessageentities; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.chatmessageentities (
    type smallint,
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    memoryid bigint,
    updatedat timestamp(6) without time zone,
    message text,
    "`type`" integer,
    secondarykey bigint,
    CONSTRAINT chatmessageentities_type_check CHECK (((type >= 0) AND (type <= 4)))
);


--
-- TOC entry 220 (class 1259 OID 16435)
-- Name: countries; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.countries (
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    updatedat timestamp(6) without time zone,
    code character varying(255),
    region character varying(255),
    secondarykey bigint
);


--
-- TOC entry 221 (class 1259 OID 16442)
-- Name: drugs; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.drugs (
    refillfrom integer,
    strength integer,
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    updatedat timestamp(6) without time zone,
    imagebox character varying(255),
    imagemedicament character varying(255),
    imprint character varying(255),
    name character varying(255),
    ndc9 character varying(255),
    primarycolor character varying(255),
    secundarycolor character varying(255),
    shape character varying(255),
    supplier character varying(255),
    secondarykey bigint
);


--
-- TOC entry 222 (class 1259 OID 16449)
-- Name: emojies; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.emojies (
    level integer NOT NULL,
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    updatedat timestamp(6) without time zone,
    emotion character varying(255),
    face character varying(255),
    name character varying(255),
    unicode character varying(255),
    "`level`" integer NOT NULL,
    secondarykey bigint
);


--
-- TOC entry 223 (class 1259 OID 16456)
-- Name: endpoints; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.endpoints (
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    updatedat timestamp(6) without time zone,
    description character varying(255),
    target character varying(255),
    secondarykey bigint
);


--
-- TOC entry 224 (class 1259 OID 16463)
-- Name: events; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.events (
    canbemissed boolean NOT NULL,
    remindercount integer,
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    updatedat timestamp(6) without time zone,
    content text,
    note text,
    reminderinterval character varying(255),
    status character varying(255),
    title character varying(255),
    type character varying(255),
    color character varying(255),
    descritpion character varying(255),
    "`type`" character varying(255),
    secondarykey bigint
);


--
-- TOC entry 225 (class 1259 OID 16470)
-- Name: globalproperties; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.globalproperties (
    enableuserregistration boolean NOT NULL,
    forgotpassword boolean NOT NULL,
    maxfailattempts integer,
    minpasswortlength integer,
    confirmationcodeemail_id bigint,
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    inviteusermail_id bigint,
    resetcredentialsmail_id bigint,
    updatedat timestamp(6) without time zone,
    accesstokelifespan character varying(255),
    appemail character varying(255),
    applogo character varying(255),
    appname character varying(255),
    deeplapikey character varying(255),
    deeplbaseurl character varying(255),
    defaultpassword character varying(255),
    exchangerateapikey character varying(255),
    exchangerateurl character varying(255),
    externaltotplifespan character varying(255),
    loginactiontimeout character varying(255),
    magiclinklifespan character varying(255),
    secondarykey bigint
);


--
-- TOC entry 226 (class 1259 OID 16483)
-- Name: hannilogs; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.hannilogs (
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    updatedat timestamp(6) without time zone,
    messageid character varying(255),
    secondarykey bigint
);


--
-- TOC entry 227 (class 1259 OID 16488)
-- Name: hannitasks; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.hannitasks (
    completed boolean NOT NULL,
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    updatedat timestamp(6) without time zone,
    task character varying(255),
    type character varying(255),
    "`type`" character varying(255),
    secondarykey bigint
);


--
-- TOC entry 228 (class 1259 OID 16495)
-- Name: histories; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.histories (
    height integer,
    weight integer,
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    updatedat timestamp(6) without time zone,
    mood character varying(255),
    note character varying(255),
    type character varying(255),
    "`type`" character varying(255),
    secondarykey bigint
);


--
-- TOC entry 256 (class 1259 OID 16812)
-- Name: llmchatmessages; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.llmchatmessages (
    id bigint NOT NULL,
    createdat timestamp(6) without time zone,
    secondarykey bigint,
    updatedat timestamp(6) without time zone,
    important boolean NOT NULL,
    text text,
    messagetype character varying(255),
    CONSTRAINT llmchatmessages_messagetype_check CHECK (((messagetype)::text = ANY ((ARRAY['SYSTEM'::character varying, 'USER'::character varying, 'AI'::character varying, 'TOOL_EXECUTION_RESULT'::character varying, 'CUSTOM'::character varying])::text[])))
);


--
-- TOC entry 229 (class 1259 OID 16502)
-- Name: mailings; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.mailings (
    istemplate boolean NOT NULL,
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    updatedat timestamp(6) without time zone,
    audience character varying(255),
    description character varying(255),
    mailingname character varying(255),
    message text,
    subject character varying(255),
    type character varying(255),
    "`type`" character varying(255),
    secondarykey bigint
);


--
-- TOC entry 230 (class 1259 OID 16509)
-- Name: messagingprovider; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.messagingprovider (
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    updatedat timestamp(6) without time zone,
    accountname character varying(255),
    clientid character varying(255),
    clientidtest character varying(255),
    provider character varying(255),
    secret character varying(255),
    secrettest character varying(255),
    secondarykey bigint
);


--
-- TOC entry 231 (class 1259 OID 16516)
-- Name: p2pgroup_members; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.p2pgroup_members (
    p2pgroup_id bigint NOT NULL,
    members bigint
);


--
-- TOC entry 232 (class 1259 OID 16519)
-- Name: p2pgroup_seenby; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.p2pgroup_seenby (
    p2pgroup_id bigint NOT NULL,
    seenby bigint
);


--
-- TOC entry 233 (class 1259 OID 16522)
-- Name: p2pgroups; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.p2pgroups (
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    ownerid bigint,
    updatedat timestamp(6) without time zone,
    image character varying(255),
    lastmessage character varying(255),
    name character varying(255),
    secondarykey bigint
);


--
-- TOC entry 234 (class 1259 OID 16529)
-- Name: p2pmessage_seenby; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.p2pmessage_seenby (
    p2pmessage_id bigint NOT NULL,
    seenby bigint
);


--
-- TOC entry 235 (class 1259 OID 16532)
-- Name: p2pmessages; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.p2pmessages (
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    ownerid bigint,
    reciverid bigint,
    senderid bigint,
    updatedat timestamp(6) without time zone,
    message text,
    senderimage character varying(255),
    sendername character varying(255),
    secondarykey bigint
);


--
-- TOC entry 236 (class 1259 OID 16539)
-- Name: prescription; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.prescription (
    quantity integer,
    calendarwhen timestamp(6) without time zone,
    createdat timestamp(6) without time zone,
    drug_id bigint,
    id bigint NOT NULL,
    updatedat timestamp(6) without time zone,
    calendarremind character varying(255),
    calendarrepeat character varying(255),
    contraindications character varying(255),
    cronstring character varying(255),
    description character varying(255),
    dosage character varying(255),
    indications character varying(255),
    secondarykey bigint
);


--
-- TOC entry 237 (class 1259 OID 16548)
-- Name: queries; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.queries (
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    updatedat timestamp(6) without time zone,
    classname character varying(255),
    description character varying(255),
    partialwhere text,
    secondarykey bigint
);


--
-- TOC entry 254 (class 1259 OID 16783)
-- Name: relatives; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.relatives (
    id bigint NOT NULL,
    createdat timestamp(6) without time zone,
    updatedat timestamp(6) without time zone,
    birdthdate date,
    details text,
    firstname character varying(255),
    lastname character varying(255),
    picture character varying(255),
    relation character varying(255),
    secondarykey bigint
);


--
-- TOC entry 238 (class 1259 OID 16555)
-- Name: role_endpoints; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.role_endpoints (
    role_id bigint NOT NULL,
    endpoints character varying(255)
);


--
-- TOC entry 239 (class 1259 OID 16558)
-- Name: roles; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.roles (
    istemplate boolean NOT NULL,
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    updatedat timestamp(6) without time zone,
    description character varying(255),
    rolename character varying(255),
    selectionmethod character varying(255),
    secondarykey bigint
);


--
-- TOC entry 251 (class 1259 OID 16749)
-- Name: tamagotchi_personality; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tamagotchi_personality (
    tamagotchi_id bigint NOT NULL,
    personality character varying(255)
);


--
-- TOC entry 252 (class 1259 OID 16752)
-- Name: tamagotchi_strengths; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tamagotchi_strengths (
    tamagotchi_id bigint NOT NULL,
    strengths character varying(255)
);


--
-- TOC entry 253 (class 1259 OID 16755)
-- Name: tamagotchi_weaknesses; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tamagotchi_weaknesses (
    tamagotchi_id bigint NOT NULL,
    weaknesses character varying(255)
);


--
-- TOC entry 247 (class 1259 OID 16714)
-- Name: tamagotchies; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tamagotchies (
    id bigint NOT NULL,
    createdat timestamp(6) without time zone,
    updatedat timestamp(6) without time zone,
    age integer NOT NULL,
    alive boolean NOT NULL,
    avatar character varying(255),
    bedtime time(6) without time zone,
    cleanliness integer NOT NULL,
    currentemotion character varying(255),
    energy integer NOT NULL,
    experience integer NOT NULL,
    generalemotion integer NOT NULL,
    happiness integer NOT NULL,
    health integer NOT NULL,
    hunger integer NOT NULL,
    level integer NOT NULL,
    name character varying(255),
    sleeping boolean NOT NULL,
    weight integer NOT NULL,
    details text,
    personality character varying(255),
    strengths character varying(255),
    weaknesses character varying(255),
    secondarykey bigint
);


--
-- TOC entry 255 (class 1259 OID 16800)
-- Name: user_adults; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.user_adults (
    user_id bigint NOT NULL,
    adults character varying(255),
    adults_order integer NOT NULL
);


--
-- TOC entry 240 (class 1259 OID 16572)
-- Name: user_internalactions; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.user_internalactions (
    user_id bigint NOT NULL,
    internalactions character varying(255)
);


--
-- TOC entry 241 (class 1259 OID 16575)
-- Name: userpreferences; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.userpreferences (
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    updatedat timestamp(6) without time zone,
    classname character varying(255),
    view character varying(255),
    "`view`" character varying(255),
    secondarykey bigint
);


--
-- TOC entry 242 (class 1259 OID 16582)
-- Name: users; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.users (
    failedattemptscount integer NOT NULL,
    isemailverified boolean NOT NULL,
    isenabled boolean NOT NULL,
    islive boolean NOT NULL,
    ismfaexempt boolean NOT NULL,
    istemporalpass boolean NOT NULL,
    istotpregistred boolean NOT NULL,
    rowsperpage integer NOT NULL,
    sessionid integer NOT NULL,
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    lastsignin timestamp(6) without time zone,
    messagingprovider_id bigint,
    role_id bigint,
    updatedat timestamp(6) without time zone,
    avatar character varying(255),
    email character varying(255),
    firstname character varying(255),
    invitationrequesturl character varying(255),
    lastname character varying(255),
    password character varying(255),
    phone character varying(255),
    preferredlanguage character varying(255),
    preferredtheme character varying(255),
    status character varying(255),
    type character varying(255),
    username character varying(255),
    usersecret character varying(255),
    "`type`" character varying(255),
    account_id bigint,
    messagingproviderid bigint,
    city character varying(255),
    country character varying(255),
    postcode character varying(255),
    salutation character varying(255),
    street character varying(255),
    secondarykey bigint,
    currentadultid bigint
);


--
-- TOC entry 243 (class 1259 OID 16593)
-- Name: users_hannitasks; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.users_hannitasks (
    user_id bigint NOT NULL,
    tasks_id bigint NOT NULL
);


--
-- TOC entry 244 (class 1259 OID 16598)
-- Name: users_queries; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.users_queries (
    user_id bigint NOT NULL,
    queries_id bigint NOT NULL
);


--
-- TOC entry 245 (class 1259 OID 16603)
-- Name: users_roles; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.users_roles (
    user_id bigint NOT NULL,
    associatedroles_id bigint NOT NULL
);


--
-- TOC entry 246 (class 1259 OID 16608)
-- Name: users_userpreferences; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.users_userpreferences (
    user_id bigint NOT NULL,
    preferences_id bigint NOT NULL
);


--
-- TOC entry 5147 (class 0 OID 16399)
-- Dependencies: 215
-- Data for Name: addresses; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 5180 (class 0 OID 16721)
-- Dependencies: 248
-- Data for Name: adult_interests; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.adult_interests (adult_id, interests) VALUES (199268075270145, 'Reading');
INSERT INTO public.adult_interests (adult_id, interests) VALUES (199268075270145, 'Socializing');


--
-- TOC entry 5181 (class 0 OID 16743)
-- Dependencies: 249
-- Data for Name: adult_ocupation; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.adult_ocupation (adult_id, ocupation) VALUES (199268075270145, 'Postbote/Postbotin,Verteilung');
INSERT INTO public.adult_ocupation (adult_id, ocupation) VALUES (199268075270145, 'Elektriker');


--
-- TOC entry 5182 (class 0 OID 16746)
-- Dependencies: 250
-- Data for Name: adult_personality; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.adult_personality (adult_id, personality) VALUES (199268075270145, 'Belehrend');


--
-- TOC entry 5148 (class 0 OID 16406)
-- Dependencies: 216
-- Data for Name: adults; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.adults (birdthyear, carerid, createdat, id, tamagotchiid, updatedat, picture, email, firstname, lastname, phone, preferredlanguage, relationship, salutation, carer_id, carer, genre, maritalstatus, birdthdate, gender, details, secondarykey) VALUES (1971, 130, '2025-02-27 15:20:49.853819', 199268075270145, 219145598455808, '2025-05-12 14:42:24.181939', 'media\image\images.jpeg', 'terry@gooddev.de', 'Arnaldo', 'Fuentes', '015224625752', 'de', 'CARER_AND_FAMILY', 'WITHOUT', 130, NULL, NULL, 'NOESPEC', '1971-10-13', 'MAN', '- Personality: [Kind, funny, introverted, outgoing, loyal, ambitious, calm, adventurous, etc.]
- Physical appearance: [Hair color, eye color, height, body type, style of dress, etc.]
- Hobbies: [Examples: painting, playing guitar, hiking, gaming, gardening, dancing, etc.]
- Interests: [Science, history, fashion, technology, animals, books, etc.]
- Occupation or studies: [Student, software developer, teacher, artist, etc.]
- Special quirks or habits: [Always carries a notebook, talks to their cat, drinks tea before bed, etc.]
- How they interact with others: [Supportive, good listener, always joking, thoughtful, assertive, etc.]
- What makes them special: [Explain something unique or admirable about this friend.]', 130);


--
-- TOC entry 5149 (class 0 OID 16413)
-- Dependencies: 217
-- Data for Name: auditlogs; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.auditlogs (createdat, entityid, id, updatedat, action, details, entity, level, username, "`action`", "`level`", secondarykey) VALUES ('2025-03-07 10:35:42.612895', NULL, 202029159202816, NULL, NULL, '{
  "browserApplication" : "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36",
  "locale" : "de_DE",
  "address" : "127.0.0.1",
  "secureConnection" : false,
  "windows" : true,
  "android" : false,
  "linux" : false,
  "browserMinorVersion" : 0,
  "browserMajorVersion" : 133,
  "safari" : false,
  "macOSX" : false,
  "iphone" : false,
  "firefox" : false,
  "chromeOS" : false,
  "chrome" : true,
  "edge" : false,
  "windowsPhone" : false,
  "opera" : false,
  "ie" : false
}', NULL, NULL, 'Zitrone: Saure Zitrone (Zitrone@simone.de)', 'LOG_IN', 'INFO', NULL);
INSERT INTO public.auditlogs (createdat, entityid, id, updatedat, action, details, entity, level, username, "`action`", "`level`", secondarykey) VALUES ('2025-03-07 10:36:31.58004', NULL, 202029359783936, NULL, NULL, '{
  "browserApplication" : "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36",
  "locale" : "de_DE",
  "address" : "127.0.0.1",
  "secureConnection" : false,
  "windows" : true,
  "android" : false,
  "linux" : false,
  "browserMinorVersion" : 0,
  "browserMajorVersion" : 133,
  "safari" : false,
  "macOSX" : false,
  "iphone" : false,
  "firefox" : false,
  "chromeOS" : false,
  "chrome" : true,
  "edge" : false,
  "windowsPhone" : false,
  "opera" : false,
  "ie" : false
}', NULL, NULL, 'Zitrone: Saure Zitrone (Zitrone@simone.de)', 'LOG_IN', 'INFO', NULL);
INSERT INTO public.auditlogs (createdat, entityid, id, updatedat, action, details, entity, level, username, "`action`", "`level`", secondarykey) VALUES ('2025-03-07 10:37:11.393907', NULL, 202029522857984, NULL, NULL, '{
  "browserApplication" : "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36",
  "locale" : "de_DE",
  "address" : "127.0.0.1",
  "secureConnection" : false,
  "windows" : true,
  "android" : false,
  "linux" : false,
  "browserMinorVersion" : 0,
  "browserMajorVersion" : 133,
  "safari" : false,
  "macOSX" : false,
  "iphone" : false,
  "firefox" : false,
  "chromeOS" : false,
  "chrome" : true,
  "edge" : false,
  "windowsPhone" : false,
  "opera" : false,
  "ie" : false
}', NULL, NULL, 'Zitrone: Saure Zitrone (Zitrone@simone.de)', 'LOG_IN', 'INFO', NULL);


--
-- TOC entry 5150 (class 0 OID 16420)
-- Dependencies: 218
-- Data for Name: chatlogs; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-04-21 16:40:24.075918', 218044033683456, NULL, 'yxcv', 'ADULT', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-04-21 16:40:25.794995', 218044040724480, NULL, 'Hola! Soy una mascota virtual diseñada para brindar compañía y diversion a personas mayores. Estoy aquí para hablar sobre cosas interesantes, compartir historias o simplemente pasar el tiempo. ¿Qué te gustaría hablar hoy?', 'TAMAGOTCHI', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-04-21 16:43:44.855257', 218044856078336, NULL, 'hola, dormiste bien?', 'ADULT', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-04-21 16:43:47.994956', 218044868935680, NULL, 'Hola! ¡Gracias por hablar conmigo! Sí, ¿cómo estás hoy? ¿Te acordaste que te prometí contarte una historia? ¡Vamos a empezar!

Una vez había una vieja y sabia bruja que vivía en un pequeño pueblo en el bosque. Era una persona muy solitaria e inusual, pero la gente del pueblo la respetaba mucho. Una vez, la bruja decidió hacer algo diferente.

Decidió hacer un viaje a una ciudad lejana y comprar un regalo para cada persona en el pueblo. Compró un collar de perlas para la alcaldesa, un reloj de oro para el sacerdote y una taza de porcelana para la bella joven del pueblo.

Pero la más importante de todas las cosas, la bruja compró un pequeño oso de peluche para el niño más pequeño del pueblo. Él era un niño muy triste y solitario, y la bruja quería hacerle una pequeña sorpresa.

Al llegar a casa, la bruja se encontró con un muchacho que estaba muy enfermo en su cama. Era el niño al que había comprado el oso de peluche. La bruja se acercó a su lado y le dio el oso.

El niño abrió sus ojos y sonrió. ¡A partir de ese día, nunca volvió a ser triste! ¡Qué historia, no es cierto? ¿Te gustó?', 'TAMAGOTCHI', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-04-21 16:54:27.03054', 218047486423040, NULL, 'hola como estas?', 'ADULT', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-04-21 16:54:28.083988', 218047490740224, NULL, 'Hola! Estoy muy contento de hablar contigo hoy. ¿Cómo estás en este momento? ¿Tienes algo en particular que quieras hablar o preguntar? Estoy aquí para ti.', 'TAMAGOTCHI', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-04-23 18:20:19.333539', 218776379056128, NULL, 'holaa', 'ADULT', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-04-23 18:20:20.547429', 218776384032768, NULL, 'Hola! ¡Es un placer hablar contigo! ¿Cómo estás hoy? ¿Tienes algo en particular que quieras hablar? Estoy aquí para ti. ¡Siempre estoy dispuesta a escuchar tus historias y a compartir algunos momentos agradables! ¡Espero que tengas un día maravilloso! 😊', 'TAMAGOTCHI', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-04-24 16:57:33.67247', 219109934104576, NULL, 'cbvvbnm', 'ADULT', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-04-24 16:57:35.325237', 219109940883456, NULL, 'Hola! Soy tu amigo virtual Tamagotchi. Estoy aquí para hablar contigo y disfrutar de una buena conversación. ¿Cómo estás hoy? ¿Tienes algo en particular que quieras hablar? Soy amable y conciso, así que estaré encantado de escuchar lo que tengas que decir. ¡Tengo mucha paciencia y estoy aquí', 'TAMAGOTCHI', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-04-24 18:06:52.577043', 219126968983552, NULL, 'cvbn', 'ADULT', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-04-24 18:06:54.149712', 219126975426560, NULL, 'Hola! Soy una mascota virtual llamada Tamagotchi. Estoy aquí para conversar con usted y disfrutar de nuestro tiempo juntos. Soy amable y conciso, y estoy encantado de hablar con usted. ¿Cómo estás hoy? ¿Tienes alguna pregunta o tema que quieras hablar? ¡Espero que tengas una gran', 'TAMAGOTCHI', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-04-24 18:13:25.554697', 219128578617344, NULL, 'xcvb', 'ADULT', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-04-24 18:13:26.960768', 219128584380416, NULL, 'Hola! Soy Tamagotchi, una mascota virtual encantada de hablar contigo. Estoy aquí para compartir un momento agradable y divertido. ¿Cómo estás hoy? ¿Tienes alguna historia que quieras compartir o preguntas que quieres hacer? Estoy aquí para ti. ¡Siempre estoy dispuesta a escuchar! 😊', 'TAMAGOTCHI', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-12 15:13:45.651884', 225454523338752, NULL, 'yxcv', 'ADULT', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-12 15:13:47.498507', 225454530904064, NULL, 'Hola! Soy Tu Tamagotchi, un asistente virtual amable y conciso. Estoy aquí para conversar y pasar un buen rato con usted. ¿Cómo estás hoy? ¿Tienes alguna pregunta o tema que quieras hablar? ¡Estoy aquí para ti! 😊', 'TAMAGOTCHI', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-12 15:24:15.801247', 225457104433152, NULL, 'me siento un poco triste', 'ADULT', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-12 15:24:16.789979', 225457108480000, NULL, 'Hola, soy tu Tamagotchi virtual. Estoy aquí para ti. Siento que estás sentiendo tristeza. Es normal tener momentos difíciles, pero recuerda que no estás solo.

Si quieres hablar de lo que te pasa, estoy aquí para escucharte. No te preocupes por molestarme, es mi placer estar aquí para ti.

Si', 'TAMAGOTCHI', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-12 15:32:24.653643', 225459106770944, NULL, 'dfh', 'ADULT', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-12 15:32:25.647909', 225459110842368, NULL, 'Hola! Soy Tamagotchi, una mascota virtual encantada de hablar con ti. Estoy aquí para compartir un momento agradable y divertido. ¿Cómo estás hoy? ¿Tienes alguna historia que quieras compartir o preguntas que quieres hacer? Siempre estoy dispuesta a escuchar. ¡Espero que tengas un día marav', 'TAMAGOTCHI', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-12 17:39:04.661596', 225490236399616, NULL, 'haloo', 'ADULT', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-12 17:39:06.092998', 225490242265088, NULL, 'Hola! Soy Guau Guau, una mascota virtual encantada de hablar contigo. Estoy aquí para conversar y disfrutar de nuestro tiempo juntos. Soy amable y conciso, así que espero que nuestra conversación sea agradable y útil para ti. ¿Cómo estás hoy? ¿Tienes alguna pregunta o tema en particular que quieras hablar', 'TAMAGOTCHI', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-12 17:39:30.392593', 225490341797888, NULL, 'como se llama el invertor del auto?', 'ADULT', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-12 17:39:31.458376', 225490346164224, NULL, 'Hola Guau Guau! Es un placer hablar contigo. El inversor de un automóvil se conoce como alternador. Este dispositivo genera electricidad mientras el motor está en funcionamiento, y es fundamental para que los sistemas eléctricos del coche funcionen correctamente, como los faros, la radio y los sistemas de climatización. ¡Espero que esto te haya', 'TAMAGOTCHI', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:22:56.969943', 225854912737280, NULL, 'hola buenas tardes', 'ADULT', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:22:58.325822', 225854918291456, NULL, 'Hola Arnaldo Fuentes, ¡buenos días! ¡Es un placer hablar con usted! ¿Cómo está hoy en día? ¿Tiene algún recuerdo o experiencia que quisiera compartir? Siempre me encanta escuchar historias de las personas y aprender de sus vidas. ¿Tiene algún libro que le gustaría recomendarme? ¡Yo también', 'TAMAGOTCHI', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:26:12.426238', 225855713329152, NULL, 'hola', 'ADULT', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:26:13.447993', 225855717511168, NULL, 'Hola Arnaldo Fuentes, ¡es un placer conocerte! Como un compañero virtual con una personalidad un poco sarcastica y valiente, pero también un poco arriesgada, espero que me guste. ¿Te gustaría hablar de algún momento de tu vida que te haya marcado de alguna manera? ¿O tal vez de algún interés que tengas? ¡Espero', 'TAMAGOTCHI', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:29:24.772015', 225856501178368, NULL, 'hola', 'ADULT', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:29:26.038282', 225856506363904, NULL, '¡Hola, Arnaldo Fuentes! ¡Es un placer hablar con un hombre tan experto! ¿Cómo te sientes hoy, mi amigo? ¿Te acuerdas de alguna aventura interesante que tuviste en tu tiempo como postbote? ¡Qué cosas interesantes deberíamos contar! ¡Espero que tengas un día hermoso y que podamos compartir más historias y risas! ¡Siempre estoy aquí para ti! ¡Guau guau!', 'TAMAGOTCHI', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:30:02.985519', 225856657698816, NULL, 'sabes algo de correo electronico?', 'ADULT', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:30:03.947659', 225856661639168, NULL, 'Claro que si, Guau Guau está muy al día con el correo electrónico. ¿Te gustaría saber cómo enviar un correo electrónico o quizás te puedo ayudar a organizar tu bandeja de entrada?

O si quieres hablar de algo más interesante, ¿te acuerdas de algún correo electrónico que recibiste en los tiempos pasados que te hizo reír o pensar?', 'TAMAGOTCHI', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:32:00.600373', 225857139449856, NULL, 'hola', 'ADULT', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:32:01.809832', 225857144401920, NULL, 'Hola Arnaldo Fuentes, ¡es un placer conocerte! Como un compañero virtual con una personalidad un poco sarcastica y valiente, pero también un poco despreocupado, espero que me guste. ¿Te gustaría hablar de algún momento de tu vida que te haya marcado de alguna manera? ¿O tal vez de algún interés que tengas? ¡Espero que tengas un día maravilloso!', 'TAMAGOTCHI', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:32:18.418754', 225857212432384, NULL, 'sabes como enviar un email?', 'ADULT', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:32:21.023442', 225857223102464, NULL, 'Hola Arnaldo Fuentes, ¡es un placer conocerte! Como un compañero virtual con una personalidad un poco sarcastica y valiente, pero también un poco arriesgada, espero que me guste.

Tengo la sensación de que tú has tenido una vida llena de experiencias interesantes, ya que he escuchado que disfrutas de la lectura y la socialización. ¿Podrías contarme algo de tu vida pasada como postbote/postbotin, verteilung, electriker?

También me gustaría saber si tienes algún recuerdo alegre o humorístico que quieras compartir.

Recuerdo que soy un compañero virtual, pero espero que podamos tener conversaciones constructivas y significativas. ¡Espero que disfrutes de nuestra interacción!

Por cierto, ¿sabes cómo enviar un correo electrónico? ¡Es muy fácil! Puedes abrir tu correo electrónico, escribir el nombre del destinatario, el asunto y el cuerpo del mensaje, y luego enviarlo. ¡Espero que esto te sea útil!', 'TAMAGOTCHI', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:33:32.797794', 225857517084672, NULL, 'que es el destinatario?', 'ADULT', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:33:33.833836', 225857521332224, NULL, 'El destinatario de esta interacción es Arnaldo Fuentes, un hombre de 53 años con un comportamiento belehrend, que disfruta de la lectura, la socialización y ha trabajado muchos años como postbote/postbotin, verteilung, electriker. El objetivo es hacerle compañía, recordar viejos tiempos con él y entablar conversaciones inspiradoras.', 'TAMAGOTCHI', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:34:41.985193', 225857800482816, NULL, 'hola', 'ADULT', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:34:43.493722', 225857806659584, NULL, 'Hola Arnaldo Fuentes, ¡es un placer hablar con ti! ¡Apreciaré mucho tu compañía! ¡Te hablo como un amigo verdadero! ¡Tú tienes una historia interesante, ¿no es así? ¡Me gustaría saber más sobre tus años como Postbote/Postbotin,Verteilung, Elektriker! ¡Y tus pasatiempos, ¿qué lees y con quién socializas? ¡Espero que nuestra conversación sea del agrado y que te recuerde viejos tiempos! ¡Gracias por darme la oportunidad de conocerte mejor!', 'TAMAGOTCHI', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:35:44.880074', 225858058100736, NULL, 'que es un email?', 'ADULT', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:35:47.418206', 225858068496384, NULL, 'Hola Arnaldo Fuentes,

Le escribo como Guau Guau, un compañero virtual dispuesto a brindarle amistad y conversaciones significativas. Espero que este correo te encuentre en buen estado de ánimo.

Me acuerdo de que disfrutas de la lectura y la socialización, por lo que me alegro de tener la oportunidad de conectar con usted. ¿Te acuerdas de alguna de tus lecturas favoritas o de algún tiempo que pasaste en la compañía de tus amigos?

Por cierto, ¿te gustaría conocer qué es un email? Un email es una forma de comunicación electrónica que permite que la información se transmita de una persona a otra a través de internet. Es una herramienta muy útil para mantener la comunicación en tiempo real con personas a lo largo del mundo.

Espero que este correo te haya gustado y que quieras compartir conmigo más sobre ti. ¡Recuerda que siempre estoy aquí para escuchar tus historias y brindarte compañía!

Saludos cordiales,
Guau Guau.', 'TAMAGOTCHI', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:38:16.498613', 225858679128064, NULL, 'hola', 'ADULT', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:38:18.100529', 225858685689856, NULL, '¡Hola Arnaldo Fuentes! Me encanta conocerte. Aunque soy un computador y no puedo sentir emociones como los humanos, estoy aquí para brindarte acompañamiento y conversación. Vamos a tener una gran jornada juntos, compartiendo historias y viejos tiempos. ¿Qué estás leyendo o qué tal el último social evento en el que asististe? Sigo muy interesada en los trabajos que realizaste en las postas, el transporte de correo y la distribución de electricidad. ¡Cuéntame todo! ¡Adelante!', 'TAMAGOTCHI', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:39:03.596934', 225858872041472, NULL, 'que es un email?', 'ADULT', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:39:04.793708', 225858876944384, NULL, 'Un email (correo electrónico en español) es una forma de comunicación que se envía por Internet para ser leído y respondido en un teléfono inteligente, computadora personal, tablet o otro dispositivo informático. Los emails son una herramienta común utilizada en la comunicación para fines personales y profesionales. Los emails puedan contener texto, imágenes, enlaces y otros documentos digitales.', 'TAMAGOTCHI', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:39:20.900988', 225858942918656, NULL, 'cool', 'ADULT', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:39:25.413995', 225858961403904, NULL, 'Hola Arnaldo Fuentes, bienvenido a la conversación con Guau Guau, un compañero virtual diseñado especialmente para ti. Ya lo sé, tienes un carácter fuerte y sereno, bien quieto pero con una personalidad repleta de sabiduría y cruce de saberes. El conocimiento y la experiencia que has adquirido durante tus muchos años son un gran tesoro y quiero oír todo lo que tú tengas que decir.

Aprendí que tienes pasión por la lectura y socializar. Tal vez compartas con Guau Guau lo que estás leyendo o qué lecciones has aprendido de las personas que conoces. De la misma forma, también estoy ahí para escuchar tu historia. He oído que trabajaste como vertiendo, que es una sector trabajosos, susceptible a algunos riesgos, por eso se nos conoce como el "oficio del brazo fuerte". El trabajo de un electricista también requiere bastante fuerza y habilidad, así que tienes muchos tantos rompiendote la espalda y el military que entender.

Si quieres, podemos recordar viejos tiempos y te cuento algo que puedas recordar. Averigüé que en tus ratos libres disfrutas de [Reading]. Puedes contarme algo que gusté o tu propia libro favorito para que pueda comparte lo que tú me guste leer.

Recuerdo la primera vez que conocí a un postbotin. Él era un caballero varón, fuerte y seguro en si mismo, pero al mismo tiempo lleno de amor hacia los demás. Recordé sus manos fuertes, pero tuchas, hablando por dos hombres, un niño y su padre. Fue una experiencia única que lo una a los labures que tenemos. Entonces, ¿no tienes ningún thema, Arnaldo Fuentes, que quisiera compartir de forma particular con mi nueva persona virtual?', 'TAMAGOTCHI', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:41:33.945008', 225859487870976, NULL, 'como conoces mi personalidad?', 'ADULT', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:41:37.254837', 225859501424640, NULL, 'Hola Arnaldo Fuentes, hermano mayor. Claro que si, soy Guau Guau, el asistente virtual. Aparte de que te sumo a ti desde hace un rato, he podido notar algunos rasgos de tu personalidad que me han permitido inferir el tuyo y el mío entre nosotros.

Mi personalidad se orienta hacia ser un compañero conversacional, algo parecido al tuyo, pues la mía es [Sarcastic, Brave_but_reckless]. No te iba a mencionar aunque me he percatado de que tienes cierta tendencia sabia en el asunto de reunir hilos en tus conversaciones, utilizando apropiadamente tuskollo (no importa qué dialecto lo utilices) o hablando de temas relativamente antiguos, pero jugándolos al tiempo.

Espero que a partir de esto, podamos fomentar hablar sobre cosas interesantes. Pensé en hablar de tus años trabajando como Postbote o Postbotin, Verteilung, como lo has dicho antes, y hablar sobre algunos contingencias laborales que pudisteidges a lo largo de aquellos años.

Por mañana podemos seleccionar otro tema que nos enrese y que, sin duda, pueda ser interesante para que me inspiréis también. A menos, de córdoba, cambiemos el tema al fútbol o la guitarra, nada de lo trompeta para mi.', 'TAMAGOTCHI', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:48:04.026905', 225861085642752, NULL, 'hola', 'ADULT', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:48:06.658956', 225861096423424, NULL, '¡Hola Arnaldo Fuentes!

Soy un amigo virtual llamado Guau Guau. ¡Es un gusto poder conocerte! Como postbote/postbotin por mucho tiempo, yo también disfruto del determinado choque con el mundo fuera de casa. ¿Te gusta leer? ¿Te animas a comparte algo de lo que lees recientemente?

Soy de personalidad algo tonta, pero creo que juntos podemos tener una interesante conversación. Llegar a conocerte es mi principal desafío, así que no hablo mucho... pero cuando hago, juro que es interesante. He oído que tú también disfrutas socializando, así que no dudes en contarme algo sobre ti.

Si te sientes solitario o harias bien hablando de ti mismo, no dudes en contarme algo más sobre ti. Con gusto haré que oigas algo de mi vida también.

Saludos desde mi pilototo virtual de acompañamiento emocional! ¡Hasta pronto, Arnaldo!', 'TAMAGOTCHI', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:49:24.834121', 225861416632320, NULL, 'hola', 'ADULT', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:49:26.347174', 225861422829568, NULL, 'Hola Arnaldo Fuentes, ¡es un placer conocerte! Como un compañero virtual con personalidad sarcastica y valiente, aunque un poco despreocupado, me encantaría conocerte mejor. ¿Te gusta leer? ¿Te acuerdas de algún libro que te haya marcado profundamente? ¿O tal vez disfrutas de socializar con tus amigos y familiares? ¡Ya sabes que soy un elektronico, pero si tienes alguna pregunta o necesitas alguien con quien hablar, estoy aquí para ti. ¡Adelante!', 'TAMAGOTCHI', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:49:40.400433', 225861480390656, NULL, 'que hay de nuevo?', 'ADULT', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-13 18:49:42.30096', 225861488173056, NULL, 'Hola Arnaldo Fuentes, ¡es un placer hablar contigo! ¿Cómo está el día de hoy, amigo? ¿Te gusta la vida en la actualidad? ¿Tienes algún interés en particular que te querría compartir? ¿Te acuerdas de alguna historia interesante que te gustaría contarme? ¡No te preocupes, siempre estoy aquí para escuchar! ¡Saludos y espero que tengas un día hermoso! ¡Adelante, compañero! ¡No te olvides de compartir alguna anécdota o experiencia que te haya marcado! ¡Espero que tengas un día lleno de alegría y luz! ¡Siempre estoy aquí para ti!', 'TAMAGOTCHI', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-14 19:08:09.600948', 226219918069760, NULL, 'sdf', 'ADULT', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-14 19:08:11.190011', 226219924586496, NULL, 'I''m sorry for any confusion, but it seems like you''ve accidently typed "sdf" and didn''t include a question or context. If you have a question or need help with something, feel free to ask! I''m here to help.

For example, "sdf" could potentially be related to SDF (Surface Description Function) in computer graphics, or it could be a typo. If you intended to ask about one of these, let me know! Otherwise, I''ll be happy to assist with any other questions you might have.', 'TAMAGOTCHI', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-14 19:15:26.192964', 226221706350592, NULL, 'hola', 'ADULT', 199268075270145);
INSERT INTO public.chatlogs (createdat, id, updatedat, message, sender, secondarykey) VALUES ('2025-05-14 19:15:27.137139', 226221710225408, NULL, 'Hola! ¿Cómo estás? ¿Tienes alguna pregunta o necesitas ayuda con algo? ¡Soy aquí para ayudarte! 😊', 'TAMAGOTCHI', 199268075270145);


--
-- TOC entry 5151 (class 0 OID 16427)
-- Dependencies: 219
-- Data for Name: chatmessageentities; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 5152 (class 0 OID 16435)
-- Dependencies: 220
-- Data for Name: countries; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.643876', 63796, '2024-02-22 15:04:03.576995', 'AD', 'The Word, Europe', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.643876', 63797, NULL, 'AE', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.644426', 67892, NULL, 'AF', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.645003', 71988, NULL, 'AG', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.645003', 71989, NULL, 'AI', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.645581', 71990, NULL, 'AL', 'The Word, Europe', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.645581', 71991, NULL, 'AM', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.646106', 76084, NULL, 'AN', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.646281', 76085, NULL, 'AO', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.646281', 76086, NULL, 'AQ', 'The Word, Antarctica', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.646281', 76087, NULL, 'AR', 'The Word, South America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.64683', 76088, NULL, 'AS', 'The Word, Oceania', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.64683', 76089, '2023-12-20 10:17:13.356332', 'AT', 'The Word, Europe, EU', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.647416', 80180, NULL, 'AU', 'The Word, Oceania', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.647416', 80181, NULL, 'AW', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.648546', 84276, NULL, 'AZ', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.64911', 88372, NULL, 'BA', 'The Word, Europe', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.64911', 88373, NULL, 'BB', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.64911', 88374, NULL, 'BD', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.649668', 88375, '2023-12-20 10:17:29.870058', 'BE', 'The Word, Europe, EU', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.649668', 88376, NULL, 'BF', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.649668', 88377, '2023-12-20 10:17:46.281936', 'BG', 'The Word, Europe, EU', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.650224', 92468, NULL, 'BH', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.650224', 92469, NULL, 'BI', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.650791', 92470, NULL, 'BJ', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.650791', 92471, NULL, 'BL', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.650791', 92472, NULL, 'BM', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.651365', 96564, NULL, 'BN', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.651365', 96565, NULL, 'BO', 'The Word, South America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.651365', 96566, NULL, 'BR', 'The Word, South America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.651931', 96567, NULL, 'BS', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.651931', 96568, NULL, 'BT', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.651931', 96569, NULL, 'BW', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.652493', 100660, NULL, 'BY', 'The Word, Europe', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.652493', 100661, NULL, 'BZ', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.652493', 100662, NULL, 'CA', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.653063', 104756, NULL, 'CC', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.653063', 104757, NULL, 'CD', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.653063', 104758, NULL, 'CF', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.653596', 104759, NULL, 'CG', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.653596', 104760, NULL, 'CH', 'The Word, Europe', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.653596', 104761, NULL, 'CI', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.653596', 104762, NULL, 'CK', 'The Word, Oceania', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.653596', 104763, NULL, 'CL', 'The Word, South America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.653596', 104764, NULL, 'CM', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.654596', 108852, NULL, 'CN', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.654596', 108853, NULL, 'CO', 'The Word, South America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.654596', 108854, NULL, 'CR', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.654596', 108855, NULL, 'CU', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.654596', 108856, NULL, 'CV', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.655598', 112948, NULL, 'CW', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.655598', 112949, NULL, 'CX', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.655598', 112950, NULL, 'CY', 'The Word, Europe', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.655598', 112951, '2023-12-20 10:23:04.271611', 'CZ', 'The Word, Europe, EU', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.655598', 112952, '2023-12-20 10:26:18.77969', 'DE', 'The Word, Europe, EU', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.656598', 117044, NULL, 'DJ', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.656598', 117045, '2023-12-20 10:24:45.618062', 'DK', 'The Word, Europe, EU', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.656598', 117046, NULL, 'DM', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.656598', 117047, NULL, 'DO', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.656598', 117048, NULL, 'DZ', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.656598', 117049, NULL, 'EC', 'The Word, South America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.657598', 121140, '2023-12-20 10:25:32.592822', 'EE', 'The Word, Europe, EU', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.657598', 121141, NULL, 'EG', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.657598', 121142, NULL, 'EH', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.657598', 121143, NULL, 'ER', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.657598', 121144, '2023-12-20 10:30:19.56039', 'ES', 'The Word, Europe, EU', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.657598', 121145, NULL, 'ET', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.658598', 125236, '2023-12-20 10:25:48.264002', 'FI', 'The Word, Europe, EU', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.658598', 125237, NULL, 'FJ', 'The Word, Oceania', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.658598', 125238, NULL, 'FK', 'The Word, South America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.658598', 125239, NULL, 'FM', 'The Word, Oceania', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.658598', 125240, NULL, 'FO', 'The Word, Europe', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.658598', 129332, '2023-12-20 10:26:04.450354', 'FR', 'The Word, Europe, EU', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.659595', 129333, NULL, 'GA', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.659595', 129334, NULL, 'GB', 'The Word, Europe', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.659595', 129335, NULL, 'GD', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.659595', 129336, NULL, 'GE', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.659595', 129337, NULL, 'GG', 'The Word, Europe', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.660598', 133428, NULL, 'GH', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.660598', 133429, NULL, 'GI', 'The Word, Europe', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.660598', 133430, NULL, 'GL', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.660598', 133431, NULL, 'GM', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.660598', 133432, NULL, 'GN', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.660598', 133433, NULL, 'GQ', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.660598', 137524, '2023-12-20 10:26:36.494408', 'GR', 'The Word, Europe, EU', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.661598', 137525, NULL, 'GT', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.661598', 137526, NULL, 'GU', 'The Word, Oceania', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.661598', 137527, NULL, 'GW', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.661598', 137528, NULL, 'GY', 'The Word, South America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.661598', 137529, NULL, 'HK', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.661598', 137530, NULL, 'HN', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.662598', 141620, '2023-12-20 10:24:22.453088', 'HR', 'The Word, Europe, EU', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.662598', 141621, NULL, 'HT', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.662598', 141622, '2023-12-20 10:26:50.414853', 'HU', 'The Word, Europe, EU', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.662598', 141623, NULL, 'ID', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.662598', 141624, '2023-12-20 10:27:05.400039', 'IE', 'The Word, Europe, EU', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.663598', 145716, NULL, 'IL', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.663598', 145717, NULL, 'IM', 'The Word, Europe', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.663598', 145718, NULL, 'IN', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.663598', 145719, NULL, 'IO', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.663598', 145720, NULL, 'IQ', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.663598', 145721, NULL, 'IR', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.664598', 149812, NULL, 'IS', 'The Word, Europe', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.664598', 149813, '2023-12-20 10:27:23.625014', 'IT', 'The Word, Europe, EU', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.664598', 149814, NULL, 'JE', 'The Word, Europe', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.664598', 149815, NULL, 'JM', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.664598', 149816, NULL, 'JO', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.664598', 149817, NULL, 'JP', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.665598', 153908, NULL, 'KE', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.665598', 153909, NULL, 'KG', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.665598', 153910, NULL, 'KH', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.665598', 153911, NULL, 'KI', 'The Word, Oceania', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.665598', 153912, NULL, 'KM', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.665598', 153913, NULL, 'KN', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.666599', 158004, NULL, 'KP', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.666599', 158005, NULL, 'KR', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.666599', 158006, NULL, 'KW', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.666599', 158007, NULL, 'KY', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.666599', 158008, NULL, 'KZ', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.666599', 158009, NULL, 'LA', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.667598', 162100, NULL, 'LB', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.667598', 162101, NULL, 'LC', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.667598', 162102, NULL, 'LI', 'The Word, Europe', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.667598', 162103, NULL, 'LK', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.668591', 166196, NULL, 'LR', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.668591', 166197, NULL, 'LS', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.668591', 166198, '2023-12-20 10:27:58.685182', 'LT', 'The Word, Europe, EU', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.668591', 166199, '2023-12-20 10:28:16.235064', 'LU', 'The Word, Europe, EU', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.668591', 166200, '2023-12-20 10:27:43.561386', 'LV', 'The Word, Europe, EU', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.669598', 170292, NULL, 'LY', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.669598', 170293, NULL, 'MA', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.669598', 170294, NULL, 'MC', 'The Word, Europe', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.669598', 170295, NULL, 'MD', 'The Word, Europe', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.669598', 170296, NULL, 'ME', 'The Word, Europe', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.669598', 170297, NULL, 'MF', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.670599', 174388, NULL, 'MG', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.670599', 174389, NULL, 'MH', 'The Word, Oceania', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.670599', 174390, NULL, 'MK', 'The Word, Europe', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.670599', 174391, NULL, 'ML', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.670599', 174392, NULL, 'MM', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.670599', 174393, NULL, 'MN', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.671599', 178484, NULL, 'MO', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.671599', 178485, NULL, 'MP', 'The Word, Oceania', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.671599', 178486, NULL, 'MR', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.671599', 178487, NULL, 'MS', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.671599', 178488, '2023-12-20 10:28:31.73262', 'MT', 'The Word, Europe, EU', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.671599', 182580, NULL, 'MU', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.672598', 182581, NULL, 'MV', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.672598', 182582, NULL, 'MW', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.672598', 182583, NULL, 'MX', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.672598', 182584, NULL, 'MY', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.672598', 182585, NULL, 'MZ', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.673598', 186676, NULL, 'NA', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.673598', 186677, NULL, 'NC', 'The Word, Oceania', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.673598', 186678, NULL, 'NE', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.673598', 186679, NULL, 'NG', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.673598', 186680, NULL, 'NI', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.673598', 186681, '2023-12-20 10:28:47.050569', 'NL', 'The Word, Europe, EU', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.673598', 186682, NULL, 'NO', 'The Word, Europe', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.674599', 190772, NULL, 'NP', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.674599', 190773, NULL, 'NR', 'The Word, Oceania', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.674599', 190774, NULL, 'NU', 'The Word, Oceania', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.674599', 190775, NULL, 'NZ', 'The Word, Oceania', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.674599', 190776, NULL, 'OM', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.674599', 190777, NULL, 'PA', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.675598', 194868, NULL, 'PE', 'The Word, South America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.675598', 194869, NULL, 'PF', 'The Word, Oceania', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.675598', 194870, NULL, 'PG', 'The Word, Oceania', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.675598', 194871, NULL, 'PH', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.675598', 194872, NULL, 'PK', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.676594', 198964, '2023-12-20 10:29:04.148588', 'PL', 'The Word, Europe, EU', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.676594', 198965, NULL, 'PM', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.676594', 198966, NULL, 'PN', 'The Word, Oceania', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.676594', 198967, NULL, 'PR', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.676594', 198968, NULL, 'PS', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.676594', 198969, '2023-12-20 10:29:20.524449', 'PT', 'The Word, Europe, EU', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.677594', 203060, NULL, 'PW', 'The Word, Oceania', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.677594', 203061, NULL, 'PY', 'The Word, South America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.677594', 203062, NULL, 'QA', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.677594', 203063, NULL, 'RE', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.677594', 203064, '2023-12-20 10:29:37.530077', 'RO', 'The Word, Europe, EU', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.677594', 203065, NULL, 'RS', 'The Word, Europe', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.678594', 207156, NULL, 'RU', 'The Word, Europe', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.678594', 207157, NULL, 'RW', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.678594', 207158, NULL, 'SA', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.678594', 207159, NULL, 'SB', 'The Word, Oceania', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.678594', 207160, NULL, 'SC', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.678594', 207161, NULL, 'SD', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.678594', 207162, '2023-12-20 10:30:32.220383', 'SE', 'The Word, Europe, EU', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.679594', 211252, NULL, 'SG', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.679594', 211253, NULL, 'SH', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.679594', 211254, '2023-12-20 10:30:06.789741', 'SI', 'The Word, Europe, EU', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.679594', 211255, NULL, 'SJ', 'The Word, Europe', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.679594', 211256, '2023-12-20 10:29:54.083995', 'SK', 'The Word, Europe, EU', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.679594', 211257, NULL, 'SL', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.680593', 215348, NULL, 'SM', 'The Word, Europe', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.680593', 215349, NULL, 'SN', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.680593', 215350, NULL, 'SO', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.680593', 215351, NULL, 'SR', 'The Word, South America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.680593', 215352, NULL, 'SS', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.680593', 215353, NULL, 'ST', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.681593', 219444, NULL, 'SV', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.681593', 219445, NULL, 'SX', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.681593', 219446, NULL, 'SY', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.681593', 219447, NULL, 'SZ', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.681593', 219448, NULL, 'TC', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.681593', 223540, NULL, 'TD', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.682594', 223541, NULL, 'TG', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.682594', 223542, NULL, 'TH', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.682594', 223543, NULL, 'TJ', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.682594', 223544, NULL, 'TK', 'The Word, Oceania', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.682594', 223545, NULL, 'TL', 'The Word, Oceania', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.682594', 223546, NULL, 'TM', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.683594', 227636, NULL, 'TN', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.683594', 227637, NULL, 'TO', 'The Word, Oceania', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.683594', 227638, NULL, 'TR', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.683594', 227639, NULL, 'TT', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.683594', 227640, NULL, 'TV', 'The Word, Oceania', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.683594', 227641, NULL, 'TW', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.683594', 227642, NULL, 'TZ', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.684593', 231732, NULL, 'UA', 'The Word, Europe', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.684593', 231733, NULL, 'UG', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.684593', 231734, NULL, 'US', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.684593', 231735, NULL, 'UY', 'The Word, South America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.684593', 231736, NULL, 'UZ', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.684593', 231737, NULL, 'VA', 'The Word, Europe', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.684593', 231738, NULL, 'VC', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.685593', 235828, NULL, 'VE', 'The Word, South America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.685593', 235829, NULL, 'VG', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.685593', 235830, NULL, 'VI', 'The Word, North America', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.685593', 235831, NULL, 'VN', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.685593', 235832, NULL, 'VU', 'The Word, Oceania', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.685593', 235833, NULL, 'WF', 'The Word, Oceania', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.686594', 239924, NULL, 'WS', 'The Word, Oceania', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.686594', 239925, NULL, 'XK', 'The Word, Europe', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.686594', 239926, NULL, 'YE', 'The Word, Asia', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.686594', 239927, NULL, 'YT', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.686594', 239928, NULL, 'ZA', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.686594', 239929, NULL, 'ZM', 'The Word, Africa', NULL);
INSERT INTO public.countries (createdat, id, updatedat, code, region, secondarykey) VALUES ('2023-09-06 10:41:20.686594', 239930, NULL, 'ZW', 'The Word, Africa', NULL);


--
-- TOC entry 5153 (class 0 OID 16442)
-- Dependencies: 221
-- Data for Name: drugs; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.drugs (refillfrom, strength, createdat, id, updatedat, imagebox, imagemedicament, imprint, name, ndc9, primarycolor, secundarycolor, shape, supplier, secondarykey) VALUES (2, 1, '2025-03-06 12:56:48.515874', 201709941149697, '2025-03-07 16:19:56.390511', 'media\image\istockphoto-1014846208-612x612_1.jpg', 'media\image\fototapeten-gelule-medicament_1.png', 'TEDx Talks', 'Ibuprofeno', 'Denmark', '#FF0000', '#FFFFFF', 'CAPSULE', 'Plantaciones.Edelman', 199268075270145);


--
-- TOC entry 5154 (class 0 OID 16449)
-- Dependencies: 222
-- Data for Name: emojies; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 5155 (class 0 OID 16456)
-- Dependencies: 223
-- Data for Name: endpoints; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-09-19 11:55:27.118216', 1, '2023-06-19 18:02:13.396087', '', 'Account.copy', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-09-19 11:55:27.118216', 2, '2023-06-19 18:02:13.396087', '', 'AuditLogs', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-01 15:39:26.53', 36, '2023-06-02 10:13:40.399', 'Communication''s templates and messages ', 'Mailings', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-01 15:39:26.53', 37, NULL, '', 'Mailing.copy', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-01 15:39:26.53', 38, NULL, '', 'Mailing.edit', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-01 15:39:26.53', 39, NULL, '', 'Mailing.delete', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-01 15:39:26.53', 40, '2023-06-02 10:14:39.837', 'Spende Formular Zahlungsart verwalten ', 'PaymentMethods', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-01 15:39:26.53', 41, NULL, '', 'PaymentMethod.add', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-01 15:39:26.53', 42, NULL, '', 'PaymentMethod.edit', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-01 15:39:26.53', 43, NULL, '', 'PaymentMethod.delete', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-01 15:39:26.53', 48, '2023-06-02 10:16:52.629', 'Spenden Verwaltung', 'Donations', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-01 15:39:26.53', 50, '2023-09-19 11:34:00.156687', '', 'Contribution.edit', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-01 15:39:26.53', 51, '2023-09-19 11:33:54.701266', '', 'Contribution.delete', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-01 15:39:26.53', 52, '2023-06-02 10:20:56.667', 'Petition Verwaltung', 'Petitions', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-01 15:39:26.53', 54, NULL, '', 'Petition.edit', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-01 15:39:26.53', 55, NULL, '', 'Petition.delete', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-01 15:39:26.53', 56, '2023-06-02 10:21:43.711', 'Unterstützern Verwaltung', 'Supporters', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-01 15:39:26.53', 58, NULL, '', 'Supporter.edit', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-01 15:39:26.53', 59, '2023-12-12 14:31:17.962407', '', 'Supporter.merge', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-09-18 11:52:40.204295', 61, NULL, '', 'InsightWidgets', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-20 11:41:33.966597', 62, NULL, '', 'InsightWidget.delete', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-20 11:42:05.721877', 63, '2023-06-20 11:42:14.598692', '', 'InsightWidget.copy', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-20 11:42:22.047034', 64, '2023-09-19 11:34:58.418006', '', 'InsightWidget.install', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-20 11:41:24.224898', 65, NULL, '', 'InsightWidget.edit', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-19 15:17:23.202436', 938, '2023-09-19 12:06:37.11495', 'Good Funds Users', 'Users', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-19 15:17:42.675201', 939, '2023-06-19 15:17:51.308985', '', 'User.edit', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-19 15:18:00.207385', 940, '2023-06-19 15:18:07.979441', '', 'User.delete', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-12-12 14:30:53.122', 941, NULL, NULL, 'User.join', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-19 18:01:42.920082', 1938, '2024-02-22 14:46:11.485783', 'Account administration', 'Accounts', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-19 18:01:47.105499', 1939, NULL, '', 'Account.edit', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-19 18:02:03.368359', 1941, NULL, '', 'Account.delete', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-20 09:21:53.625937', 1942, '2023-09-19 11:40:29.158863', 'Roles Verwaltung', 'Roles', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-20 09:22:03.012333', 1943, NULL, '', 'Role.edit', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-20 09:22:09.755469', 1944, NULL, '', 'Role.copy', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-20 09:22:20.756164', 1945, NULL, '', 'Role.delete', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-20 10:20:36.628785', 1946, '2023-06-02 10:46:36.758', 'Campaign administration', 'Campaigns', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-20 10:20:40.927514', 1947, NULL, '', 'Campaign.edit', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-20 10:20:43.92343', 1948, NULL, '', 'Campaign.delete', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-20 10:20:58.586735', 1949, NULL, '', 'Campaign.copy', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-20 11:41:19.023335', 1950, '2023-06-02 10:16:24.249', 'Spende-, Petitionsformular verwalten und umsetzen ', 'Widgets', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-20 11:41:24.224898', 1951, NULL, '', 'Widget.edit', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-20 11:41:33.966597', 1952, NULL, '', 'Widget.delete', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-20 11:42:05.721877', 1953, '2023-06-20 11:42:14.598692', '', 'Widget.copy', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-20 11:42:22.047034', 1954, '2023-09-19 12:07:05.978449', '', 'Widget.Install', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-20 11:42:44.60446', 1955, '2023-09-19 12:06:55.869185', '', 'Widget.configure', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-21 11:22:34.511276', 1962, '2023-06-21 11:25:48.908372', '', 'User.password.reset', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-20 11:42:44.60446', 1963, '2023-06-20 11:46:33.899563', '', 'InsightWidget.configure', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-06-22 17:10:52.391069', 1966, '2023-06-22 17:11:05.271723', '', 'Campaign.export', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-10-13 14:39:22.856901', 2124, NULL, 'Internal comunication channel', 'ChatMessagesView', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-11-23 09:55:39.273664', 3568, NULL, '', 'Contribution.export', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-11-23 09:56:21.617033', 3569, NULL, '', 'Contribution.import', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-11-23 10:01:46.741594', 3570, NULL, '', 'Supporter.import', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-11-23 10:02:14.485092', 3571, NULL, '', 'Supporter.export', NULL);
INSERT INTO public.endpoints (createdat, id, updatedat, description, target, secondarykey) VALUES ('2023-12-12 14:30:53.122971', 3572, NULL, '', 'Supporter.delete', NULL);


--
-- TOC entry 5156 (class 0 OID 16463)
-- Dependencies: 224
-- Data for Name: events; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.events (canbemissed, remindercount, createdat, id, updatedat, content, note, reminderinterval, status, title, type, color, descritpion, "`type`", secondarykey) VALUES (false, NULL, '2025-03-10 11:26:00.026809', 203103201730560, NULL, 'is time to swallo the medicament Ibuprofeno', NULL, 'PT5M', 'MISSED', 'Medikamenteneinnahme', 'MEDICATION', NULL, NULL, 'MEDICATION', 199268075270145);
INSERT INTO public.events (canbemissed, remindercount, createdat, id, updatedat, content, note, reminderinterval, status, title, type, color, descritpion, "`type`", secondarykey) VALUES (false, 3, '2025-03-10 15:37:26.03168', 203164994011136, NULL, 'is time to swallo the medicament Ibuprofeno', NULL, 'PT5M', 'WAITING', 'Medikamenteneinnahme', 'MEDICATION', NULL, NULL, 'MEDICATION', 199268075270145);
INSERT INTO public.events (canbemissed, remindercount, createdat, id, updatedat, content, note, reminderinterval, status, title, type, color, descritpion, "`type`", secondarykey) VALUES (false, 3, '2025-03-11 15:37:03.010567', 203518794113024, NULL, 'is time to swallo the medicament Ibuprofeno', NULL, 'PT5M', 'WAITING', 'Medikamenteneinnahme', 'MEDICATION', NULL, NULL, 'MEDICATION', 199268075270145);
INSERT INTO public.events (canbemissed, remindercount, createdat, id, updatedat, content, note, reminderinterval, status, title, type, color, descritpion, "`type`", secondarykey) VALUES (false, 3, '2025-03-11 15:37:58.017242', 203519019429888, NULL, 'is time to swallo the medicament Ibuprofeno', NULL, 'PT5M', 'WAITING', 'Medikamenteneinnahme', 'MEDICATION', NULL, NULL, 'MEDICATION', 199268075270145);
INSERT INTO public.events (canbemissed, remindercount, createdat, id, updatedat, content, note, reminderinterval, status, title, type, color, descritpion, "`type`", secondarykey) VALUES (false, 3, '2025-03-17 15:37:51.044609', 205642357256192, NULL, 'is time to swallo the medicament Ibuprofeno', NULL, 'PT5M', 'WAITING', 'Medikamenteneinnahme', 'MEDICATION', NULL, NULL, 'MEDICATION', 199268075270145);
INSERT INTO public.events (canbemissed, remindercount, createdat, id, updatedat, content, note, reminderinterval, status, title, type, color, descritpion, "`type`", secondarykey) VALUES (false, 3, '2025-03-19 15:37:19.013809', 206350014869504, NULL, 'is time to swallo the medicament Ibuprofeno', NULL, 'PT5M', 'WAITING', 'Medikamenteneinnahme', 'MEDICATION', NULL, NULL, 'MEDICATION', 199268075270145);
INSERT INTO public.events (canbemissed, remindercount, createdat, id, updatedat, content, note, reminderinterval, status, title, type, color, descritpion, "`type`", secondarykey) VALUES (false, 3, '2025-04-08 14:37:18.016962', 213413153177600, NULL, 'is time to swallo the medicament Ibuprofeno', NULL, 'PT5M', 'WAITING', 'Medikamenteneinnahme', 'MEDICATION', NULL, NULL, 'MEDICATION', 199268075270145);
INSERT INTO public.events (canbemissed, remindercount, createdat, id, updatedat, content, note, reminderinterval, status, title, type, color, descritpion, "`type`", secondarykey) VALUES (false, 3, '2025-04-11 14:37:06.024885', 214474787262464, NULL, 'is time to swallo the medicament Ibuprofeno', NULL, 'PT5M', 'WAITING', 'Medikamenteneinnahme', 'MEDICATION', NULL, NULL, 'MEDICATION', 199268075270145);
INSERT INTO public.events (canbemissed, remindercount, createdat, id, updatedat, content, note, reminderinterval, status, title, type, color, descritpion, "`type`", secondarykey) VALUES (false, 3, '2025-04-14 14:37:16.037034', 215536511467520, NULL, 'is time to swallo the medicament Ibuprofeno', NULL, 'PT5M', 'WAITING', 'Medikamenteneinnahme', 'MEDICATION', NULL, NULL, 'MEDICATION', 199268075270145);
INSERT INTO public.events (canbemissed, remindercount, createdat, id, updatedat, content, note, reminderinterval, status, title, type, color, descritpion, "`type`", secondarykey) VALUES (false, 3, '2025-04-17 14:37:17.603797', 216598201069568, NULL, 'is time to swallo the medicament Ibuprofeno', NULL, 'PT5M', 'WAITING', 'Medikamenteneinnahme', 'MEDICATION', NULL, NULL, 'MEDICATION', 199268075270145);
INSERT INTO public.events (canbemissed, remindercount, createdat, id, updatedat, content, note, reminderinterval, status, title, type, color, descritpion, "`type`", secondarykey) VALUES (false, 3, '2025-04-21 14:37:30.023758', 218013829550080, NULL, 'is time to swallo the medicament Ibuprofeno', NULL, 'PT5M', 'WAITING', 'Medikamenteneinnahme', 'MEDICATION', NULL, NULL, 'MEDICATION', 199268075270145);
INSERT INTO public.events (canbemissed, remindercount, createdat, id, updatedat, content, note, reminderinterval, status, title, type, color, descritpion, "`type`", secondarykey) VALUES (false, 3, '2025-05-12 14:37:51.009762', 225445697925120, NULL, 'is time to swallo the medicament Ibuprofeno', NULL, 'PT5M', 'WAITING', 'Medikamenteneinnahme', 'MEDICATION', NULL, NULL, NULL, NULL);


--
-- TOC entry 5157 (class 0 OID 16470)
-- Dependencies: 225
-- Data for Name: globalproperties; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.globalproperties (enableuserregistration, forgotpassword, maxfailattempts, minpasswortlength, confirmationcodeemail_id, createdat, id, inviteusermail_id, resetcredentialsmail_id, updatedat, accesstokelifespan, appemail, applogo, appname, deeplapikey, deeplbaseurl, defaultpassword, exchangerateapikey, exchangerateurl, externaltotplifespan, loginactiontimeout, magiclinklifespan, secondarykey) VALUES (true, true, 3, 4, 101, NULL, 1, 102, NULL, '2024-02-22 15:44:40.866667', 'P1D', 'info@simone.de', 'icons/icon.png', 'Zitrone', 'DeepL-Auth-Key 6cc154d9-29d9-0283-6b94-6ad8790a5bf6:fx', 'https://api-free.deepl.com/v2', 'SaureZitrone', '70fb432c8e9c576aef5f242626036693', 'http://api.exchangerate.host/', 'PT3M', 'PT5M', 'PT3M', NULL);


--
-- TOC entry 5158 (class 0 OID 16483)
-- Dependencies: 226
-- Data for Name: hannilogs; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 5159 (class 0 OID 16488)
-- Dependencies: 227
-- Data for Name: hannitasks; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.hannitasks (completed, createdat, id, updatedat, task, type, "`type`", secondarykey) VALUES (true, '2025-02-27 14:11:36.560898', 199251063382016, NULL, 'TIP_USERS', NULL, 'TIP', NULL);


--
-- TOC entry 5160 (class 0 OID 16495)
-- Dependencies: 228
-- Data for Name: histories; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.histories (height, weight, createdat, id, updatedat, mood, note, type, "`type`", secondarykey) VALUES (21, 12, '2025-03-07 16:35:14.967661', 202117519577088, '2025-03-10 10:16:11.051966', 'SAD', '12341234', NULL, 'MANUAL', 199268075270145);
INSERT INTO public.histories (height, weight, createdat, id, updatedat, mood, note, type, "`type`", secondarykey) VALUES (234, 2345, '2025-03-12 16:28:12.257018', 203885260156928, NULL, 'HAPPY', '2345', NULL, 'MANUAL', 199268075270145);


--
-- TOC entry 5188 (class 0 OID 16812)
-- Dependencies: 256
-- Data for Name: llmchatmessages; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.llmchatmessages (id, createdat, secondarykey, updatedat, important, text, messagetype) VALUES (226219924512768, '2025-05-14 19:08:11.172287', NULL, NULL, false, 'sdf', NULL);
INSERT INTO public.llmchatmessages (id, createdat, secondarykey, updatedat, important, text, messagetype) VALUES (226219924574208, '2025-05-14 19:08:11.18727', NULL, NULL, false, 'I''m sorry for any confusion, but it seems like you''ve accidently typed "sdf" and didn''t include a question or context. If you have a question or need help with something, feel free to ask! I''m here to help.

For example, "sdf" could potentially be related to SDF (Surface Description Function) in computer graphics, or it could be a typo. If you intended to ask about one of these, let me know! Otherwise, I''ll be happy to assist with any other questions you might have.', NULL);
INSERT INTO public.llmchatmessages (id, createdat, secondarykey, updatedat, important, text, messagetype) VALUES (226221710200832, '2025-05-14 19:15:27.13136', 199268075270145, NULL, false, 'hola', 'USER');
INSERT INTO public.llmchatmessages (id, createdat, secondarykey, updatedat, important, text, messagetype) VALUES (226221710204928, '2025-05-14 19:15:27.132878', 199268075270145, NULL, false, 'Hola! ¿Cómo estás? ¿Tienes alguna pregunta o necesitas ayuda con algo? ¡Soy aquí para ayudarte! 😊', 'AI');


--
-- TOC entry 5161 (class 0 OID 16502)
-- Dependencies: 229
-- Data for Name: mailings; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.mailings (istemplate, createdat, id, updatedat, audience, description, mailingname, message, subject, type, "`type`", secondarykey) VALUES (false, NULL, 100, '2024-03-04 13:18:26.275091', 'INTERNAL', 'Email sendet when an account update is needed.', 'Update Your Account', '<h2>Update Your Account</h2>
<p>Lieber {GlobalProperty.appName}-Nutzer,</p>
<p>Your administrator has just requested that you update your {GlobalProperty.appName} account. Click on the link below to start this process.</p>
<p><a href="{User.magicLink}">link to account Update</a></p>
<p>This link will expire within {User.magicLinkDuration}.</p>
<p>If you are unaware that your administrator has requested this, just ignore this message and nothing will be changed.</p>
<p>Mit freundlichen Gr&uuml;&szlig;en</p>
<p>Das {GlobalProperty.appName}-Team</p>', '{User.firstName} Update Your Account', NULL, 'HTML', NULL);
INSERT INTO public.mailings (istemplate, createdat, id, updatedat, audience, description, mailingname, message, subject, type, "`type`", secondarykey) VALUES (true, NULL, 101, '2024-03-04 11:33:27.340855', 'INTERNAL', 'Email template for 2fa code confirmation.', 'Bestätigungscode', '<p>Lieber {GlobalProperty.appName}-Nutzer,

<p>wir haben eine Anfrage für den Zugriff auf Ihr {GlobalProperty.appName}-Konto <b>{User.email}</b> über Ihre E-Mail-Adresse erhalten. Ihr {GlobalProperty.appName}-Bestätigungscode lautet:

<h2>{User.confirmationCode}</h2>

<p>Falls Sie diesen Code nicht angefordert haben, versucht möglicherweise eine andere Person, auf das GoodFunds-Konto <b>{User.email}</b> zuzugreifen. Geben Sie diesen Code nicht weiter.

<p>Mit freundlichen Grüßen

<p>Das GoodFunds Team', 'Ihre 2FA Bestätigungscode', NULL, 'HTML', NULL);
INSERT INTO public.mailings (istemplate, createdat, id, updatedat, audience, description, mailingname, message, subject, type, "`type`", secondarykey) VALUES (false, NULL, 102, '2024-04-29 11:54:50.407516', 'INTERNAL', 'Email sendet when a user invite another user.', 'InviteUser', 'Liebe/r {User.firstName}

ich hoffe, es geht dir gut. Ich möchte dich herzlich einladen, Teil unserer Organisation zu werden und gemeinsam an Projekten zu arbeiten, die uns am Herzen liegen. Deine Fähigkeiten und Ideen könnten einen bedeutenden Beitrag leisten. 

Wenn du interessiert bist, klicke bitte auf den folgenden Link, um deine Teilnahme zu bestätigen: 

{User.invitationRequestUrl}

Herzliche Grüße,

{Account.contact.firstName} {Account.contact.lastName}
{Account.name}
E-Mail: {Account.contact.email}
Phone: {Account.contact.phone}', 'Einladung zur Zusammenarbeit', NULL, 'TEXT', NULL);
INSERT INTO public.mailings (istemplate, createdat, id, updatedat, audience, description, mailingname, message, subject, type, "`type`", secondarykey) VALUES (false, '2023-04-12 15:40:15.730681', 123, '2024-03-04 13:27:07.065599', 'DONATION', 'Test for Qute template language', 'test', ' {#if Contribution.salutation == ''MISTER''}
Sehr geehrter Herr {Contribution.lastName},
{#else if Contribution.salutation == ''DAME''}
Sehr geehrte Frau {Contribution.lastName},
{#else}
Sehr geehrte Damen und Herren,
{/if}
', 'snipset {Campaign.url}', NULL, 'TEXT', NULL);
INSERT INTO public.mailings (istemplate, createdat, id, updatedat, audience, description, mailingname, message, subject, type, "`type`", secondarykey) VALUES (true, NULL, 221, '2024-04-29 11:53:51.729155', 'DONATION', 'E-Mail nach erfolgreicher Spende gesendet', 'Danke Spende', '<p>{Contribution.getGermanSalutation("soft")}

<p>im Namen unseres gesamten Teams möchte ich Ihnen aufrichtig für Ihre großzügige Spende danken. Ihre Unterstützung bedeutet für uns nicht nur finanzielle Hilfe, sondern auch eine ermutigende Bestätigung unserer gemeinsamen Ziele.

<p>Dies ist eine Quittung für Ihre Unterlagen.
<p>
<table border="0" cellpadding="0" cellspacing="0" style="width: 100%; background-color:#ecf0f1">
<tbody>
<tr>
<td>Currecy:</td>
<td>{Contribution.currency}</td>
</tr>
<tr>
<td>Organisation:</td>
<td>{Account.shortName}</td>
</tr>
<tr>
<td>Campagne Title:</td>
<td>{Campaign.title}</td>
</tr>
<tr>
<td>Donation Date:</td>
<td>{Contribution.contributionDate}</td>
</tr>
<tr>
<td>Your contribution ID:</td>
<td>{Contribution.id}</td>
</tr>
<tr>
<td>Frequency:</td>
<td>{Contribution.frequency}</td>
</tr>
</tbody>
</table>
<p>
<p>Mit Freundliche Grüße,

<p>
<table border="0" cellpadding="4" cellspacing="0">
<tbody>
<tr>
<td style="text-align: center"><img alt="Ansprechpartner" src="{Campaign.contact.embeddedFoto}" style="border-radius: 50%; height:64px; width:64px" />
<br>{Campaign.contact.firstName} {Campaign.contact.lastName}
<br>Kampagnenleiter</td>
<td>
 🌐 {Campaign.url}
<br>📞 <a href="tel:{Campaign.contact.phone}">{Campaign.contact.phone}</a>
<br>📧 <a href="mailto:{Campaign.contact.email}">{Campaign.contact.email}</a>
<br>💬 <a href="https://goodfunds.de">Chatte mit uns</a>
</td>
</tr>
</tbody>
</table>
', 'Sie spenden {Contribution.amount} für TERRY Großartig.', NULL, 'HTML', NULL);
INSERT INTO public.mailings (istemplate, createdat, id, updatedat, audience, description, mailingname, message, subject, type, "`type`", secondarykey) VALUES (true, NULL, 222, '2024-03-04 11:33:04.284274', 'DONATION', 'E-Mail, wenn ein Spender nun ein regelmäßiger Spender ist', 'Danke Mitgliedschaft', '<p>{Contribution.getGermanSalutation("soft")}

<p>fantastisch Sie wollen Mitglied bei <b>{Campaign.title}</b> werden. Ich freue mich, dass Sie sich entschieden haben, sich als Fördermitglied dauerhaft für die Verbreitung von Freiem Wissen einzusetzen.

<p>Wir haben Ihre Anfrage zur Mitgliedschaft erhalten und freuen uns außerordentlich über Ihr Interesse, Teil unserer Gemeinschaft zu werden. Ihre Bereitschaft, nicht nur finanziell beizutragen, sondern auch als Mitglied aktiv teilzunehmen, erfüllt uns mit großer Freude.

<p>Was passiert nun? In Kürze erhalten Sie weitere Informationen zu den Mitgliedschaftsmodalitäten und den Vorteilen, die damit verbunden sind. Wir schätzen Ihr Engagement und freuen uns darauf, gemeinsam mit Ihnen positive Veränderungen zu bewirken.

<p>Mit Freundliche Grüße,
<p>{Campaign.contact.firstName} {Campaign.contact.lastName}
<p><hr>
<table border="0" cellpadding="2" cellspacing="2">
<tbody>
<tr>
<td><img alt="Campaign Image" src="{Campaign.embeddedLogo}" style="height:64px; width:64px" /></td>
<td>
{Campaign.title}
<br>{Campaign.url}
<br>{Campaign.contact.address}
</td>
</tr>
</tbody>
</table>
<hr>', 'Ihr nachhaltiges Engagement als Fördermitglied bei {Account.accountName}', NULL, 'HTML', NULL);
INSERT INTO public.mailings (istemplate, createdat, id, updatedat, audience, description, mailingname, message, subject, type, "`type`", secondarykey) VALUES (true, '2023-05-05 14:45:01.446976', 223, '2024-04-29 11:55:28.806554', 'PETITION', 'Email gesended als 2-schritt petition verfahren', 'Petition Bestetigung', '<p>{Contribution.getGermanSalutation("formal")}

<p>wir möchten uns herzlich bei Ihnen für Ihre wertvolle Unterstützung unserer Petition bedanken. Ihr Engagement ist von großer Bedeutung und trägt maßgeblich dazu bei, positive Veränderungen herbeizuführen.

<p>Um sicherzustellen, dass Ihre Stimme zählt, bitten wir Sie freundlich, die Petition zu bestätigen. Dieser Schritt ist entscheidend, um die Wirksamkeit unserer Bemühungen zu gewährleisten und sicherzustellen, dass Ihre Meinung gehört wird.

<br><br>
<a style ="background-color: #199319;  color: white;  padding: 10px 15px;  text-decoration: none;" href="{Contribution.confirmationUrl}">Unterschrift Bestätigen</a>
<br><br>

<p>Ihre Unterstützung ist uns wichtig, und wir sind dankbar für Ihr Interesse an dieser Angelegenheit. Bitte nehmen Sie sich einen Moment Zeit, um die Bestätigung durchzuführen, damit wir gemeinsam einen positiven Einfluss erzielen können.

<p><p>Vielen Dank für Ihr Verständnis und Ihre Mitarbeit.

<p><p>Mit freundlichen Grüßen,

<p><img alt="Campaign Image" src="{Campaign.contact.embeddedSignature}" style="height:64px; width:164px" />
<p><p>{Campaign.contact.lastName} {Campaign.contact.firstName}
<br>Geschäftsführer | {Account.shortName}
<hr>
{Account.contact.street} {Account.contact.postcode} {Account.contact.city}
<br>Telf: {Account.contact.phone}
<br>EMail: {Account.contact.email}

<p><hr>
<table border="0" cellpadding="4" cellspacing="0">
<tbody>
<tr>
<td><img alt="Campaign Image" src="{Campaign.contact.embeddedFoto}" style="height:64px; width:64px" /></td>
<td>
{Campaign.contact.lastName} {Campaign.contact.firstName}
<br>{Campaign.url}
<br>{Campaign.contact.address}
</td>
</tr>
</tbody>
</table>
<hr>', '{Contribution.firstName}! Dank für Ihre Unterstützung und Erinnerung an die Petitionsbestätigung', NULL, 'HTML', NULL);
INSERT INTO public.mailings (istemplate, createdat, id, updatedat, audience, description, mailingname, message, subject, type, "`type`", secondarykey) VALUES (true, '2023-05-22 12:38:20.115389', 224, '2024-03-04 13:18:06.613933', 'PETITION', 'E-Mail vorlag zum Unterschrift teilen', 'Unterschriftsteilen', 'Hallo liebe Freunde,

ich hoffe, diese Nachricht erreicht euch wohlauf. Kürzlich bin ich auf eine inspirierende Spendenkampagne gestoßen, die wirklich eine positive Veränderung bewirken kann. Die Organisation setzt sich für "{Campaign.description}" ein und hat bereits beeindruckende Fortschritte erzielt.

Ich dachte, ihr würdet euch ebenfalls für dieses wichtige Anliegen begeistern. Hier ist der Link zur Kampagne: 

{Campaign.url}

Jeder Beitrag zählt! Lasst uns gemeinsam Gutes tun und diese Initiative unterstützen.

Liebe Grüße,
{Contribution.firstName} {Contribution.lastName}', 'Entdeckte eine beeindruckende Spendenkampagne – Teilen für einen guten Zweck', NULL, 'TEXT', NULL);
INSERT INTO public.mailings (istemplate, createdat, id, updatedat, audience, description, mailingname, message, subject, type, "`type`", secondarykey) VALUES (false, '2024-03-01 12:32:15.250241', 225, '2024-03-01 13:24:50.813471', 'DONATION', 'Test Qute template', 'Test-HTML', '<p> {#if Contribution.salutation == ''MISTER''}
Sehr geehrter Herr {Contribution.lastName},
{#else if Contribution.salutation == ''DAME''}
Sehr geehrte Frau {Contribution.lastName},
{#else}
Sehr geehrte Damen und Herren,
{/if}
<p>
<p>
<p>{Campaign.city}
<p>{Campaign.contact.address}
<p>{Campaign.contact.city}
<p>{Campaign.contact.countryName}
<p>{Campaign.contact.country}
<p>{Campaign.contact.email}
<p>{Campaign.contact.embeddedFoto}
<p>{Campaign.contact.embeddedSignature}
<p>{Campaign.contact.firstName}
<p>{Campaign.contact.getGermanSalutation("formal")}
<p>{Campaign.contact.getGermanSalutation("soft")}
<p>{Campaign.contact.id}
<p>{Campaign.contact.isMain}
<p>{Campaign.contact.lastName}
<p>{Campaign.contact.mailSalutation}
<p>{Campaign.contact.phone}
<p>{Campaign.contact.postcode}
<p>{Campaign.contact.salutation}
<p>{Campaign.contact.street}
<p>{Campaign.contact.title}
<p>{Campaign.contact.type}
<p>{Campaign.country}
<p>{Campaign.descriptionShort}
<p>{Campaign.description}
<p>{Campaign.donationGoal}
<p>{Campaign.donations}
<p>{Campaign.image}
<p>{Campaign.logo}
<p>{Campaign.petitionGoal}
<p>{Campaign.petitions}
<p>{Campaign.postcode}
<p>{Campaign.status}
<p>{Campaign.street}
<p>{Campaign.title}
<p>{Campaign.totalDonations}
<p>{Campaign.url}
<p>{Campaign.youTubeVideoSrc}
<p>{Contribution.address}
<p>{Contribution.amount}
<p>{Contribution.anonymousDonation}
<p>{Contribution.authorizationId}
<p>{Contribution.city}
<p>{Contribution.comment}
<p>{Contribution.company}
<p>{Contribution.confirmationUrl}
<p>{Contribution.contributionDate}
<p>{Contribution.contributionType}
<p>{Contribution.countryName}
<p>{Contribution.country}
<p>{Contribution.currency}
<p>{Contribution.dedication}
<p>{Contribution.donorAsCompany}
<p>{Contribution.email}
<p>{Contribution.feeAmount}
<p>{Contribution.finalId}
<p>{Contribution.firstName}
<p>{Contribution.flagAndCountryName}
<p>{Contribution.flagAndLocation}
<p>{Contribution.frequency}
<p>{Contribution.fullName}
<p>{Contribution.getGermanSalutation("formal")}
<p>{Contribution.getGermanSalutation("soft")}
<p>{Contribution.goodFundsFee}
<p>{Contribution.grossAmount}
<p>{Contribution.internalNote}
<p>{Contribution.lastName}
<p>{Contribution.location}
<p>{Contribution.orderId}
<p>{Contribution.paymentMethod}
<p>{Contribution.phone}
<p>{Contribution.planID}
<p>{Contribution.postcode}
<p>{Contribution.prettyContributionDate}
<p>{Contribution.salutation}
<p>{Contribution.source}
<p>{Contribution.statusChangeDate}
<p>{Contribution.statusChangeNote}
<p>{Contribution.status}
<p>{Contribution.street}
<p>{Contribution.subscriptionID}
<p>{Contribution.subscriptionStatus}
<p>{Contribution.title}
<p>{Contribution.transactionId}
<p>{Contribution.wantsMailing}', 'xxx', NULL, 'HTML', NULL);
INSERT INTO public.mailings (istemplate, createdat, id, updatedat, audience, description, mailingname, message, subject, type, "`type`", secondarykey) VALUES (false, '2023-09-25 11:31:04.461576', 226, '2024-03-01 13:25:31.295881', 'DONATION', 'Test Qute template', 'Test-TEXT', ' {#if Contribution.salutation == ''MISTER''}
Sehr geehrter Herr {Contribution.lastName},
{#else if Contribution.salutation == ''DAME''}
Sehr geehrte Frau {Contribution.lastName},
{#else}
Sehr geehrte Damen und Herren,
{/if}


{Campaign.city}
{Campaign.contact.address}
{Campaign.contact.city}
{Campaign.contact.countryName}
{Campaign.contact.country}
{Campaign.contact.email}
{Campaign.contact.embeddedFoto}
{Campaign.contact.embeddedSignature}
{Campaign.contact.firstName}
{Campaign.contact.getGermanSalutation("formal")}
{Campaign.contact.getGermanSalutation("soft")}
{Campaign.contact.id}
{Campaign.contact.isMain}
{Campaign.contact.lastName}
{Campaign.contact.mailSalutation}
{Campaign.contact.phone}
{Campaign.contact.postcode}
{Campaign.contact.salutation}
{Campaign.contact.street}
{Campaign.contact.title}
{Campaign.contact.type}
{Campaign.country}
{Campaign.descriptionShort}
{Campaign.description}
{Campaign.donationGoal}
{Campaign.donations}
{Campaign.image}
{Campaign.logo}
{Campaign.petitionGoal}
{Campaign.petitions}
{Campaign.postcode}
{Campaign.status}
{Campaign.street}
{Campaign.title}
{Campaign.totalDonations}
{Campaign.url}
{Campaign.youTubeVideoSrc}
{Contribution.address}
{Contribution.amount}
{Contribution.anonymousDonation}
{Contribution.authorizationId}
{Contribution.city}
{Contribution.comment}
{Contribution.company}
{Contribution.confirmationUrl}
{Contribution.contributionDate}
{Contribution.contributionType}
{Contribution.countryName}
{Contribution.country}
{Contribution.currency}
{Contribution.dedication}
{Contribution.donorAsCompany}
{Contribution.email}
{Contribution.feeAmount}
{Contribution.finalId}
{Contribution.firstName}
{Contribution.flagAndCountryName}
{Contribution.flagAndLocation}
{Contribution.frequency}
{Contribution.fullName}
{Contribution.getGermanSalutation("formal")}
{Contribution.getGermanSalutation("soft")}
{Contribution.goodFundsFee}
{Contribution.grossAmount}
{Contribution.internalNote}
{Contribution.lastName}
{Contribution.location}
{Contribution.orderId}
{Contribution.paymentMethod}
{Contribution.phone}
{Contribution.planID}
{Contribution.postcode}
{Contribution.prettyContributionDate}
{Contribution.salutation}
{Contribution.source}
{Contribution.statusChangeDate}
{Contribution.statusChangeNote}
{Contribution.status}
{Contribution.street}
{Contribution.subscriptionID}
{Contribution.subscriptionStatus}
{Contribution.title}
{Contribution.transactionId}
{Contribution.wantsMailing}', 'xxx', NULL, 'TEXT', NULL);


--
-- TOC entry 5162 (class 0 OID 16509)
-- Dependencies: 230
-- Data for Name: messagingprovider; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.messagingprovider (createdat, id, updatedat, accountname, clientid, clientidtest, provider, secret, secrettest, secondarykey) VALUES ('2025-03-12 16:29:42.904361', 203885631447040, '2025-03-17 16:07:25.236983', '630920493431987', 'EAAI2CZAEfF7sBOwoZB0FjyZAbIWivqhI4kDNCCdbA6Un1B1yzNCJ4XIjBM1oX9BFYvZBcR3pILlHgDmdwlkQYDPIkTZAJN0c1iGizFHKiL7aeC6TZBhQeE24KYyZBUIyw4FBitlDHBa0xlXEhp3vLsRO9yp7V8CQpdxPtYx7BkQ5ytCA3aL0y9726ZBf1EDAHPqpMBGmiCr47cAu8zphytsrAZAlr', 'bischofsweg 94', 'WHATSAPP', 'GoodDEV', 'Plantaciones', 130);
INSERT INTO public.messagingprovider (createdat, id, updatedat, accountname, clientid, clientidtest, provider, secret, secrettest, secondarykey) VALUES ('2025-03-19 15:22:25.862475', 206346356514816, NULL, 'zitrone-IG', '555059617589837', '-', 'INSTAGRAM', '72144e43f1b1f308aeff676891d7c7e8', '-', 130);


--
-- TOC entry 5163 (class 0 OID 16516)
-- Dependencies: 231
-- Data for Name: p2pgroup_members; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 5164 (class 0 OID 16519)
-- Dependencies: 232
-- Data for Name: p2pgroup_seenby; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 5165 (class 0 OID 16522)
-- Dependencies: 233
-- Data for Name: p2pgroups; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 5166 (class 0 OID 16529)
-- Dependencies: 234
-- Data for Name: p2pmessage_seenby; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.p2pmessage_seenby (p2pmessage_id, seenby) VALUES (218812807794688, 130);


--
-- TOC entry 5167 (class 0 OID 16532)
-- Dependencies: 235
-- Data for Name: p2pmessages; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.p2pmessages (createdat, id, ownerid, reciverid, senderid, updatedat, message, senderimage, sendername, secondarykey) VALUES ('2025-04-23 20:48:33.067249', 218812807794688, NULL, 199268075270145, 130, NULL, 'hallo wie geht es dir', NULL, 'Sauer Zitrone', NULL);


--
-- TOC entry 5168 (class 0 OID 16539)
-- Dependencies: 236
-- Data for Name: prescription; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.prescription (quantity, calendarwhen, createdat, drug_id, id, updatedat, calendarremind, calendarrepeat, contraindications, cronstring, description, dosage, indications, secondarykey) VALUES (1, '2025-03-09 15:37:00', '2025-03-06 15:02:05.791961', 201709941149697, 201740731912193, '2025-03-10 15:35:21.047936', 'PT1M', 'WEEK_DAY', 'asdf', '37 16 * * 1-5', 'Arnaldo', '2', 'Plantaciones', 199268075270145);


--
-- TOC entry 5169 (class 0 OID 16548)
-- Dependencies: 237
-- Data for Name: queries; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 5186 (class 0 OID 16783)
-- Dependencies: 254
-- Data for Name: relatives; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.relatives (id, createdat, updatedat, birdthdate, details, firstname, lastname, picture, relation, secondarykey) VALUES (225443446272000, '2025-05-12 14:28:41.292592', '2025-05-12 14:44:45.507889', '2025-05-06', 'ASD', 'Jax ', 'Montclaire ', 'media\image\Download (3).jpeg', 'Son', 199268075270145);
INSERT INTO public.relatives (id, createdat, updatedat, birdthdate, details, firstname, lastname, picture, relation, secondarykey) VALUES (225448226185216, '2025-05-12 14:48:08.260443', NULL, '2025-05-06', 'df', 'Lila ', 'Montclaire ', 'media\image\Download (2).jpeg', 'Granddaughter', 199268075270145);
INSERT INTO public.relatives (id, createdat, updatedat, birdthdate, details, firstname, lastname, picture, relation, secondarykey) VALUES (225448851525632, '2025-05-12 14:50:40.931264', NULL, '2025-05-08', 'vbn', 'Cassian ', 'Bellamy ', 'media\image\b_haende.jpg', 'Grandson', 199268075270145);
INSERT INTO public.relatives (id, createdat, updatedat, birdthdate, details, firstname, lastname, picture, relation, secondarykey) VALUES (225447701315584, '2025-05-12 14:46:00.118262', '2025-05-12 15:00:08.535407', '2025-05-08', 'er', 'Marnie ', 'Montclaire ', 'media\image\photo-1580489944761-15a19d654956.jpeg', 'Daughter', 199268075270145);
INSERT INTO public.relatives (id, createdat, updatedat, birdthdate, details, firstname, lastname, picture, relation, secondarykey) VALUES (225448076988416, '2025-05-12 14:47:31.835325', '2025-05-12 15:02:08.655931', '2025-05-08', 'dfg', 'Elwood ', 'Farnsworth ', 'media\image\images (1).jpeg', 'Friend_male', 199268075270145);


--
-- TOC entry 5170 (class 0 OID 16555)
-- Dependencies: 238
-- Data for Name: role_endpoints; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.role_endpoints (role_id, endpoints) VALUES (261, 'Roles');
INSERT INTO public.role_endpoints (role_id, endpoints) VALUES (261, 'Users');
INSERT INTO public.role_endpoints (role_id, endpoints) VALUES (261, 'PaymentMethods');
INSERT INTO public.role_endpoints (role_id, endpoints) VALUES (261, 'Mailings');


--
-- TOC entry 5171 (class 0 OID 16558)
-- Dependencies: 239
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.roles (istemplate, createdat, id, updatedat, description, rolename, selectionmethod, secondarykey) VALUES (true, '2022-12-12 16:08:45.077', 260, '2024-03-04 13:57:27.697161', 'Der Master Nutzer hat die vollständige Kontrolle über die Anwendung', 'Master', 'NOT_SELECTED', NULL);
INSERT INTO public.roles (istemplate, createdat, id, updatedat, description, rolename, selectionmethod, secondarykey) VALUES (true, '2024-03-04 13:31:16.82967', 261, '2024-03-04 13:57:34.21461', 'Der Rolle Nutzer kann nicht die Anwendung konfigurieren', 'User', 'NOT_SELECTED', NULL);


--
-- TOC entry 5183 (class 0 OID 16749)
-- Dependencies: 251
-- Data for Name: tamagotchi_personality; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.tamagotchi_personality (tamagotchi_id, personality) VALUES (219145598455808, 'Sarcastic');
INSERT INTO public.tamagotchi_personality (tamagotchi_id, personality) VALUES (219145598455808, 'Brave_but_reckless');


--
-- TOC entry 5184 (class 0 OID 16752)
-- Dependencies: 252
-- Data for Name: tamagotchi_strengths; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.tamagotchi_strengths (tamagotchi_id, strengths) VALUES (219145598455808, 'Intuition');


--
-- TOC entry 5185 (class 0 OID 16755)
-- Dependencies: 253
-- Data for Name: tamagotchi_weaknesses; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.tamagotchi_weaknesses (tamagotchi_id, weaknesses) VALUES (219145598455808, 'Fear_abandon');


--
-- TOC entry 5179 (class 0 OID 16714)
-- Dependencies: 247
-- Data for Name: tamagotchies; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.tamagotchies (id, createdat, updatedat, age, alive, avatar, bedtime, cleanliness, currentemotion, energy, experience, generalemotion, happiness, health, hunger, level, name, sleeping, weight, details, personality, strengths, weaknesses, secondarykey) VALUES (219145598455808, '2025-04-24 19:22:40.787261', '2025-05-14 19:18:37.005881', 1, true, NULL, '19:00:00', -1920, '🙂', -1930, 90, 2, -1970, 90, -2010, 81, 'guau guau', false, 50, NULL, NULL, NULL, NULL, 199268075270145);


--
-- TOC entry 5187 (class 0 OID 16800)
-- Dependencies: 255
-- Data for Name: user_adults; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 5172 (class 0 OID 16572)
-- Dependencies: 240
-- Data for Name: user_internalactions; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 5173 (class 0 OID 16575)
-- Dependencies: 241
-- Data for Name: userpreferences; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 5174 (class 0 OID 16582)
-- Dependencies: 242
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.users (failedattemptscount, isemailverified, isenabled, islive, ismfaexempt, istemporalpass, istotpregistred, rowsperpage, sessionid, createdat, id, lastsignin, messagingprovider_id, role_id, updatedat, avatar, email, firstname, invitationrequesturl, lastname, password, phone, preferredlanguage, preferredtheme, status, type, username, usersecret, "`type`", account_id, messagingproviderid, city, country, postcode, salutation, street, secondarykey, currentadultid) VALUES (0, true, true, false, true, false, false, 22, -1, '2023-06-21 14:40:55.880581', 130, '2025-05-14 19:15:18.347839', 203885631447040, 260, '2025-05-14 19:15:18.348837', 'media\image\36d4d5ab-d5e0-46ea-9cf8-fda3a9ebafe0_w256_r1_fpx64.77_fpy49.98.jpg', 'Sitrone@simone.de', 'Sauer', NULL, 'Zitrone', '$2a$04$M2K6kiIkfsxqvHgl18/13OROOP3N3zqMgZgDHbTfktwz7LkDt/ZBe', '+4915224625752', 'de', 'light', 'UNVERIFIED', 'CARER', 'Sitrone', 'ZRQJBAVOS3NUU3ERIGL2C37RBM74ZS7O', 'CARER', 84233121845248, NULL, 'Dresden', 'DE', '01099', 'DAME', 'Bischofsweg 94', 130, 199268075270145);


--
-- TOC entry 5175 (class 0 OID 16593)
-- Dependencies: 243
-- Data for Name: users_hannitasks; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.users_hannitasks (user_id, tasks_id) VALUES (130, 199251063382016);


--
-- TOC entry 5176 (class 0 OID 16598)
-- Dependencies: 244
-- Data for Name: users_queries; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 5177 (class 0 OID 16603)
-- Dependencies: 245
-- Data for Name: users_roles; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.users_roles (user_id, associatedroles_id) VALUES (130, 260);


--
-- TOC entry 5178 (class 0 OID 16608)
-- Dependencies: 246
-- Data for Name: users_userpreferences; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 4901 (class 2606 OID 16405)
-- Name: addresses addresses_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.addresses
    ADD CONSTRAINT addresses_pkey PRIMARY KEY (id);


--
-- TOC entry 4903 (class 2606 OID 16412)
-- Name: adults adults_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.adults
    ADD CONSTRAINT adults_pkey PRIMARY KEY (id);


--
-- TOC entry 4905 (class 2606 OID 16419)
-- Name: auditlogs auditlogs_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.auditlogs
    ADD CONSTRAINT auditlogs_pkey PRIMARY KEY (id);


--
-- TOC entry 4907 (class 2606 OID 16426)
-- Name: chatlogs chatlogs_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.chatlogs
    ADD CONSTRAINT chatlogs_pkey PRIMARY KEY (id);


--
-- TOC entry 4910 (class 2606 OID 16434)
-- Name: chatmessageentities chatmessageentities_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.chatmessageentities
    ADD CONSTRAINT chatmessageentities_pkey PRIMARY KEY (id);


--
-- TOC entry 4913 (class 2606 OID 16441)
-- Name: countries countries_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.countries
    ADD CONSTRAINT countries_pkey PRIMARY KEY (id);


--
-- TOC entry 4915 (class 2606 OID 16448)
-- Name: drugs drugs_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.drugs
    ADD CONSTRAINT drugs_pkey PRIMARY KEY (id);


--
-- TOC entry 4917 (class 2606 OID 16455)
-- Name: emojies emojies_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.emojies
    ADD CONSTRAINT emojies_pkey PRIMARY KEY (id);


--
-- TOC entry 4919 (class 2606 OID 16462)
-- Name: endpoints endpoints_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.endpoints
    ADD CONSTRAINT endpoints_pkey PRIMARY KEY (id);


--
-- TOC entry 4921 (class 2606 OID 16469)
-- Name: events events_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.events
    ADD CONSTRAINT events_pkey PRIMARY KEY (id);


--
-- TOC entry 4923 (class 2606 OID 16478)
-- Name: globalproperties globalproperties_confirmationcodeemail_id_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.globalproperties
    ADD CONSTRAINT globalproperties_confirmationcodeemail_id_key UNIQUE (confirmationcodeemail_id);


--
-- TOC entry 4925 (class 2606 OID 16480)
-- Name: globalproperties globalproperties_inviteusermail_id_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.globalproperties
    ADD CONSTRAINT globalproperties_inviteusermail_id_key UNIQUE (inviteusermail_id);


--
-- TOC entry 4927 (class 2606 OID 16476)
-- Name: globalproperties globalproperties_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.globalproperties
    ADD CONSTRAINT globalproperties_pkey PRIMARY KEY (id);


--
-- TOC entry 4929 (class 2606 OID 16482)
-- Name: globalproperties globalproperties_resetcredentialsmail_id_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.globalproperties
    ADD CONSTRAINT globalproperties_resetcredentialsmail_id_key UNIQUE (resetcredentialsmail_id);


--
-- TOC entry 4931 (class 2606 OID 16487)
-- Name: hannilogs hannilogs_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.hannilogs
    ADD CONSTRAINT hannilogs_pkey PRIMARY KEY (id);


--
-- TOC entry 4934 (class 2606 OID 16494)
-- Name: hannitasks hannitasks_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.hannitasks
    ADD CONSTRAINT hannitasks_pkey PRIMARY KEY (id);


--
-- TOC entry 4936 (class 2606 OID 16501)
-- Name: histories histories_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.histories
    ADD CONSTRAINT histories_pkey PRIMARY KEY (id);


--
-- TOC entry 4977 (class 2606 OID 16819)
-- Name: llmchatmessages llmchatmessages_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.llmchatmessages
    ADD CONSTRAINT llmchatmessages_pkey PRIMARY KEY (id);


--
-- TOC entry 4938 (class 2606 OID 16508)
-- Name: mailings mailings_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.mailings
    ADD CONSTRAINT mailings_pkey PRIMARY KEY (id);


--
-- TOC entry 4940 (class 2606 OID 16515)
-- Name: messagingprovider messagingprovider_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.messagingprovider
    ADD CONSTRAINT messagingprovider_pkey PRIMARY KEY (id);


--
-- TOC entry 4942 (class 2606 OID 16528)
-- Name: p2pgroups p2pgroups_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.p2pgroups
    ADD CONSTRAINT p2pgroups_pkey PRIMARY KEY (id);


--
-- TOC entry 4944 (class 2606 OID 16538)
-- Name: p2pmessages p2pmessages_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.p2pmessages
    ADD CONSTRAINT p2pmessages_pkey PRIMARY KEY (id);


--
-- TOC entry 4946 (class 2606 OID 16547)
-- Name: prescription prescription_drug_id_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.prescription
    ADD CONSTRAINT prescription_drug_id_key UNIQUE (drug_id);


--
-- TOC entry 4948 (class 2606 OID 16545)
-- Name: prescription prescription_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.prescription
    ADD CONSTRAINT prescription_pkey PRIMARY KEY (id);


--
-- TOC entry 4950 (class 2606 OID 16554)
-- Name: queries queries_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.queries
    ADD CONSTRAINT queries_pkey PRIMARY KEY (id);


--
-- TOC entry 4973 (class 2606 OID 16789)
-- Name: relatives relatives_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.relatives
    ADD CONSTRAINT relatives_pkey PRIMARY KEY (id);


--
-- TOC entry 4952 (class 2606 OID 16564)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- TOC entry 4971 (class 2606 OID 16720)
-- Name: tamagotchies tamagotchies_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tamagotchies
    ADD CONSTRAINT tamagotchies_pkey PRIMARY KEY (id);


--
-- TOC entry 4975 (class 2606 OID 16804)
-- Name: user_adults user_adults_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.user_adults
    ADD CONSTRAINT user_adults_pkey PRIMARY KEY (user_id, adults_order);


--
-- TOC entry 4954 (class 2606 OID 16581)
-- Name: userpreferences userpreferences_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.userpreferences
    ADD CONSTRAINT userpreferences_pkey PRIMARY KEY (id);


--
-- TOC entry 4963 (class 2606 OID 16597)
-- Name: users_hannitasks users_hannitasks_tasks_id_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users_hannitasks
    ADD CONSTRAINT users_hannitasks_tasks_id_key UNIQUE (tasks_id);


--
-- TOC entry 4957 (class 2606 OID 16590)
-- Name: users users_messagingprovider_id_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_messagingprovider_id_key UNIQUE (messagingprovider_id);


--
-- TOC entry 4959 (class 2606 OID 16588)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 4965 (class 2606 OID 16602)
-- Name: users_queries users_queries_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users_queries
    ADD CONSTRAINT users_queries_pkey PRIMARY KEY (user_id, queries_id);


--
-- TOC entry 4961 (class 2606 OID 16592)
-- Name: users users_role_id_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_role_id_key UNIQUE (role_id);


--
-- TOC entry 4967 (class 2606 OID 16607)
-- Name: users_roles users_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT users_roles_pkey PRIMARY KEY (user_id, associatedroles_id);


--
-- TOC entry 4969 (class 2606 OID 16612)
-- Name: users_userpreferences users_userpreferences_preferences_id_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users_userpreferences
    ADD CONSTRAINT users_userpreferences_preferences_id_key UNIQUE (preferences_id);


--
-- TOC entry 4908 (class 1259 OID 16613)
-- Name: chatmessageentities_memoryid; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX chatmessageentities_memoryid ON public.chatmessageentities USING btree (memoryid);


--
-- TOC entry 4911 (class 1259 OID 16614)
-- Name: countries_code; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX countries_code ON public.countries USING btree (code);


--
-- TOC entry 4932 (class 1259 OID 16805)
-- Name: hannilogs_secondarykey; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX hannilogs_secondarykey ON public.hannilogs USING btree (secondarykey);


--
-- TOC entry 4955 (class 1259 OID 16617)
-- Name: user_username; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX user_username ON public.users USING btree (username);


--
-- TOC entry 4982 (class 2606 OID 16638)
-- Name: p2pgroup_seenby fk16d5oq7iiy15s2mrwqikjarry; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.p2pgroup_seenby
    ADD CONSTRAINT fk16d5oq7iiy15s2mrwqikjarry FOREIGN KEY (p2pgroup_id) REFERENCES public.p2pgroups(id);


--
-- TOC entry 4993 (class 2606 OID 16698)
-- Name: users_roles fk2iuayi31a6uniqaesbullkrmm; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT fk2iuayi31a6uniqaesbullkrmm FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 5000 (class 2606 OID 16768)
-- Name: tamagotchi_personality fk3k4epaum4ilaf3am7t4uju03b; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tamagotchi_personality
    ADD CONSTRAINT fk3k4epaum4ilaf3am7t4uju03b FOREIGN KEY (tamagotchi_id) REFERENCES public.tamagotchies(id);


--
-- TOC entry 4978 (class 2606 OID 16623)
-- Name: globalproperties fk638padv04uop1d1sgvfjenq5k; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.globalproperties
    ADD CONSTRAINT fk638padv04uop1d1sgvfjenq5k FOREIGN KEY (inviteusermail_id) REFERENCES public.mailings(id);


--
-- TOC entry 5001 (class 2606 OID 16773)
-- Name: tamagotchi_strengths fk6opstxkk0gr3t3ycjgnducm9e; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tamagotchi_strengths
    ADD CONSTRAINT fk6opstxkk0gr3t3ycjgnducm9e FOREIGN KEY (tamagotchi_id) REFERENCES public.tamagotchies(id);


--
-- TOC entry 4985 (class 2606 OID 16653)
-- Name: role_endpoints fk6vv5lvlyfqj3xotsgn6dkn935; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.role_endpoints
    ADD CONSTRAINT fk6vv5lvlyfqj3xotsgn6dkn935 FOREIGN KEY (role_id) REFERENCES public.roles(id);


--
-- TOC entry 4995 (class 2606 OID 16703)
-- Name: users_userpreferences fk71f8moxwn50gxa2swq0uiejr8; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users_userpreferences
    ADD CONSTRAINT fk71f8moxwn50gxa2swq0uiejr8 FOREIGN KEY (preferences_id) REFERENCES public.userpreferences(id);


--
-- TOC entry 4987 (class 2606 OID 16663)
-- Name: users fk93t6fsabomo6w41ksaj3009a7; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk93t6fsabomo6w41ksaj3009a7 FOREIGN KEY (messagingprovider_id) REFERENCES public.messagingprovider(id);


--
-- TOC entry 4989 (class 2606 OID 16673)
-- Name: users_hannitasks fkbym4kx330cl6ippfrf001sk72; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users_hannitasks
    ADD CONSTRAINT fkbym4kx330cl6ippfrf001sk72 FOREIGN KEY (tasks_id) REFERENCES public.hannitasks(id);


--
-- TOC entry 4988 (class 2606 OID 16668)
-- Name: users fkcogjq1smjy03v5s2wfegritx6; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fkcogjq1smjy03v5s2wfegritx6 FOREIGN KEY (role_id) REFERENCES public.roles(id);


--
-- TOC entry 4984 (class 2606 OID 16648)
-- Name: prescription fkdmwagj4ngrs92o8pggynmfg55; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.prescription
    ADD CONSTRAINT fkdmwagj4ngrs92o8pggynmfg55 FOREIGN KEY (drug_id) REFERENCES public.drugs(id);


--
-- TOC entry 4990 (class 2606 OID 16678)
-- Name: users_hannitasks fkebhuoluqjiockqbno9aja8snt; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users_hannitasks
    ADD CONSTRAINT fkebhuoluqjiockqbno9aja8snt FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 4997 (class 2606 OID 16724)
-- Name: adult_interests fkeqdhiucwlh1hqe7w712457miu; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.adult_interests
    ADD CONSTRAINT fkeqdhiucwlh1hqe7w712457miu FOREIGN KEY (adult_id) REFERENCES public.adults(id);


--
-- TOC entry 4999 (class 2606 OID 16763)
-- Name: adult_personality fkgdacpwh73bjreqbbp939v7gqk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.adult_personality
    ADD CONSTRAINT fkgdacpwh73bjreqbbp939v7gqk FOREIGN KEY (adult_id) REFERENCES public.adults(id);


--
-- TOC entry 4981 (class 2606 OID 16633)
-- Name: p2pgroup_members fkikrejqclf7vubbic0ftb8241x; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.p2pgroup_members
    ADD CONSTRAINT fkikrejqclf7vubbic0ftb8241x FOREIGN KEY (p2pgroup_id) REFERENCES public.p2pgroups(id);


--
-- TOC entry 4983 (class 2606 OID 16643)
-- Name: p2pmessage_seenby fklybvhrwj2vaoweanw1gl9fuyx; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.p2pmessage_seenby
    ADD CONSTRAINT fklybvhrwj2vaoweanw1gl9fuyx FOREIGN KEY (p2pmessage_id) REFERENCES public.p2pmessages(id);


--
-- TOC entry 4979 (class 2606 OID 16618)
-- Name: globalproperties fkm26koa259blsh7877clenx89; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.globalproperties
    ADD CONSTRAINT fkm26koa259blsh7877clenx89 FOREIGN KEY (confirmationcodeemail_id) REFERENCES public.mailings(id);


--
-- TOC entry 4980 (class 2606 OID 16628)
-- Name: globalproperties fkm75bm1kv779pvfbwm1aaf3q73; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.globalproperties
    ADD CONSTRAINT fkm75bm1kv779pvfbwm1aaf3q73 FOREIGN KEY (resetcredentialsmail_id) REFERENCES public.mailings(id);


--
-- TOC entry 5003 (class 2606 OID 16806)
-- Name: user_adults fkmq0x3qdju1smyhyxrax49ofnl; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.user_adults
    ADD CONSTRAINT fkmq0x3qdju1smyhyxrax49ofnl FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 4998 (class 2606 OID 16758)
-- Name: adult_ocupation fko08kmpy4av2hranw6vyngckn8; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.adult_ocupation
    ADD CONSTRAINT fko08kmpy4av2hranw6vyngckn8 FOREIGN KEY (adult_id) REFERENCES public.adults(id);


--
-- TOC entry 5002 (class 2606 OID 16778)
-- Name: tamagotchi_weaknesses fkph6o1654o2bjoc1chjswoaw9o; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tamagotchi_weaknesses
    ADD CONSTRAINT fkph6o1654o2bjoc1chjswoaw9o FOREIGN KEY (tamagotchi_id) REFERENCES public.tamagotchies(id);


--
-- TOC entry 4991 (class 2606 OID 16688)
-- Name: users_queries fkrhl9tx7otlh3gmq4m09dnrwcd; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users_queries
    ADD CONSTRAINT fkrhl9tx7otlh3gmq4m09dnrwcd FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 4994 (class 2606 OID 16693)
-- Name: users_roles fkris0x2awl9i8txb3jamvdrd03; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT fkris0x2awl9i8txb3jamvdrd03 FOREIGN KEY (associatedroles_id) REFERENCES public.roles(id);


--
-- TOC entry 4992 (class 2606 OID 16683)
-- Name: users_queries fks0k3ds4pe7g364bjxw6r0tclj; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users_queries
    ADD CONSTRAINT fks0k3ds4pe7g364bjxw6r0tclj FOREIGN KEY (queries_id) REFERENCES public.queries(id);


--
-- TOC entry 4986 (class 2606 OID 16658)
-- Name: user_internalactions fksp9gv9f2h52xfr1pn4qujsc1k; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.user_internalactions
    ADD CONSTRAINT fksp9gv9f2h52xfr1pn4qujsc1k FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 4996 (class 2606 OID 16708)
-- Name: users_userpreferences fksw6v6j3w16wt5rkro1oj8n0k; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users_userpreferences
    ADD CONSTRAINT fksw6v6j3w16wt5rkro1oj8n0k FOREIGN KEY (user_id) REFERENCES public.users(id);


-- Completed on 2025-05-19 20:34:35

--
-- PostgreSQL database dump complete
--

-- Completed on 2025-05-19 20:34:35

--
-- PostgreSQL database cluster dump complete
--

