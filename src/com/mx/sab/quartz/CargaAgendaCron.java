package com.mx.sab.quartz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import lombok.extern.log4j.Log4j2;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.IAseguradorDao;
import com.mx.sab.dao.ICargaAgendaDao;
import com.mx.sab.dao.IGenericDao;
import com.mx.sab.enums.CatEstadosEnum;
import com.mx.sab.enums.CatSexoEnum;
import com.mx.sab.enums.TipoIdentificadorEnum;
import com.mx.sab.model.Afiliado;
import com.mx.sab.model.AfiliadoAsegurador;
import com.mx.sab.model.AfiliadoAseguradorId;
import com.mx.sab.model.Afiliadotipoidentificador;
import com.mx.sab.model.AfiliadotipoidentificadorId;
import com.mx.sab.model.Agenda;
import com.mx.sab.model.Aseguradores;
import com.mx.sab.model.Catagendadopor;
import com.mx.sab.model.Catestados;
import com.mx.sab.model.Catsexos;
import com.mx.sab.model.Cattipoafiliado;
import com.mx.sab.model.Cattipoidentificador;
import com.mx.sab.model.Usuarios;
import com.mx.sab.model.jndi.AfiliadoTipoIdentificador;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.vo.CargaAfiliadosVo;
import com.mx.sab.vo.CargaAgendaVo;

@Log4j2
public class CargaAgendaCron extends QuartzJobBean {
	
	private static final String APPLICATION_CONTEXT_KEY = "applicationContext";
	
    private ApplicationContext getApplicationContext(JobExecutionContext context ){
        ApplicationContext appCtx = null;
        try{
        	appCtx = (ApplicationContext)context.getScheduler().getContext().get(APPLICATION_CONTEXT_KEY);
            if (appCtx == null) {
                throw new JobExecutionException("No application context available in scheduler context for key \"" + APPLICATION_CONTEXT_KEY + "\"");
            }	
        } catch (JobExecutionException e) {
			e.printStackTrace();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
        
        return appCtx;
    }
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		//log.info("inicio cron");
		ApplicationContext appCtx = getApplicationContext(arg0);
		System.out.println("RRG INICIO");
		IAgendaDao agendaDao = appCtx.getBean(IAgendaDao.class);
		IGenericDao genericDao = appCtx.getBean(IGenericDao.class);
		int idAsegurador = 10;
		FileOutputStream fop = null;	
		
		try{
			String carpeta = FormatUtil.getFechaCarpeta();
	        String hostname = "192.168.55.201";
	        String login = "ftp";
	        String password = "c1taM";
	        String directoryIn = "/home/istemed5/isste_citasMedicas/";	        
	        String directoryOut = "C:/Users/Raúl Ríos/Documents/"+carpeta+"/";

	        java.util.Properties config = new java.util.Properties();
	        config.put("StrictHostKeyChecking", "no");
	 
	        JSch ssh = new JSch();
	        Session session = ssh.getSession(login, hostname, 22);
	        session.setConfig(config);
	        session.setPassword(password);
	        session.connect();
	        Channel channel = session.openChannel("sftp");
	        channel.connect();
	 
	        ChannelSftp sftp = (ChannelSftp) channel;
	        sftp.cd(directoryIn);
	        copyAllFiles(sftp, directoryIn, directoryOut, carpeta);
	        
	        channel.disconnect();
	        session.disconnect();
	        
			File file = new File(directoryOut+"Log.txt");
			fop = new FileOutputStream(file);
			fop.flush();
			fop.close();
			
			creartxtAgenda(directoryOut, carpeta);
			File filePacientes = new File(directoryOut+"issste-pacientes-"+carpeta+".txt");
			afiliados(appCtx, agendaDao, genericDao, filePacientes, idAsegurador, directoryOut);
	        File fileCitas = new File(directoryOut+"Citas.txt");
	        agenda(appCtx, fileCitas, idAsegurador, directoryOut);

		} catch (JSchException e) {
			e.printStackTrace();
		} catch (SftpException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void agenda(ApplicationContext appCtx, File file, int idAsegurador, String directoryOut) {
		try{
			writeLog(directoryOut, "Metodo agenda");
			IAgendaDao agendaDao = appCtx.getBean(IAgendaDao.class);
//			IGenericDao genericDao = appCtx.getBean(IGenericDao.class);
//			ILugarAtencionDao lugarAtencionDao = appCtx.getBean(ILugarAtencionDao.class);
			IAseguradorDao aseguradorDao = appCtx.getBean(IAseguradorDao.class);
//			IPrestadoresDao prestadoresDao = appCtx.getBean(IPrestadoresDao.class);
//			IUsuarioDao usuarioDao = appCtx.getBean(IUsuarioDao.class);
			Aseguradores aseguradores = aseguradorDao.getAseguradorById(idAsegurador);
			Catagendadopor catagendadopor = agendaDao.getCatAgendadoPor(1);
			ICargaAgendaDao cargaAgendaDao = appCtx.getBean(ICargaAgendaDao.class);
			
			FileInputStream fis = null;
			
			fis = new FileInputStream(file);
	
	        BufferedReader br = null;
	        
	    	int posicionPrestador = 0;
	    	int posicionClaveInst = 1;
	    	int posicionCveMedico = 2;
	    	int posicionConsultorio = 3;
	    	int posicionConsultorioOriginal = 4;
	    	int posicionEstatusCita = 5;
	    	int posicionClaveCita = 6;
	    	int posicionAsegurador = 7;
	    	int posicionTipoIden = 8;
	    	int posicionAfiliado = 9;
	    	int posicionConvenio = 10;
	    	int posicionFecha = 11;
	    	int posicionHora = 12;
	    	int posicionBeneficiario = 14;
	    	int posicionCodigo = 15;
	    	int posicionSubCodigo = 16;
	    	int posicionDescripcionCodigo = 17;
	    	Collection<CargaAgendaVo> cargaAgendaVos = new ArrayList<>();
	        br = new BufferedReader(new InputStreamReader(fis));
	    	String line;
	        while ((line = br.readLine()) != null) {
	        	String[] datos = line.split("\\|"); 
				CargaAgendaVo cargaAgendaVo = null;
				for(int i=0; i<datos.length; i++){
					if(i == posicionPrestador){
						cargaAgendaVo = new CargaAgendaVo();
						cargaAgendaVo.setPrestador(datos[i]);
					}else if(i == posicionClaveInst){
						cargaAgendaVo.setClaveInst(datos[i]);
					}else if(i == posicionCveMedico){
						cargaAgendaVo.setCveMedico(datos[i]);
					}else if(i == posicionConsultorio){
						cargaAgendaVo.setConsultorio(datos[i]);
					}else if(i == posicionConsultorioOriginal){
						cargaAgendaVo.setConsultorioOriginal(datos[i]);
					}else if(i == posicionEstatusCita){
						cargaAgendaVo.setEstatusCita(datos[i]);
					}else if(i == posicionClaveCita){
						cargaAgendaVo.setClaveCita(datos[i]);
					}else if(i == posicionAsegurador){
						cargaAgendaVo.setAsegurador(datos[i]);
					}else if(i == posicionTipoIden){
						cargaAgendaVo.setTipoIden(datos[i]);
					}else if(i == posicionAfiliado){
						cargaAgendaVo.setAfiliado(datos[i]);
					}else if(i == posicionConvenio){
						cargaAgendaVo.setConvenio(datos[i]);
					}else if(i == posicionFecha){
						cargaAgendaVo.setFecha(datos[i]);
					}else if(i == posicionHora){
						cargaAgendaVo.setHora(datos[i]);
					}else if(i == posicionBeneficiario){
						cargaAgendaVo.setBeneficiario(datos[i]);		
					}else if(i == posicionCodigo){
						cargaAgendaVo.setCodigo(datos[i]);
					}else if(i == posicionSubCodigo){
						cargaAgendaVo.setSubCodigo(datos[i]);
					}else if(i == posicionDescripcionCodigo){
						cargaAgendaVo.setDescripcionCodigo(datos[i]);
					}
				}
				cargaAgendaVos.add(cargaAgendaVo);
	        }
	        
	        Agenda agenda = new Agenda();
	        for (CargaAgendaVo cargaAgendaVo : cargaAgendaVos) {
	        	try{
					if (cargaAgendaVo.getClaveInst().equals("9321900")) {
						Afiliado afiliado = null;
						if (cargaAgendaVo.getBeneficiario()==null) {
							Collection<AfiliadoTipoIdentificador> afiliadotipoidentificadors = cargaAgendaDao.getAfiliadoTipoIdentificadorIsssteJNDI(Integer.parseInt(cargaAgendaVo.getAfiliado()));
							boolean existeBeneficiario = false;
							for (AfiliadoTipoIdentificador afiliadotipoidentificador : afiliadotipoidentificadors) {
								Collection<AfiliadoTipoIdentificador> afiliadoTipoIdentificadors2 = cargaAgendaDao.getAfiliadoTipoIdentificadorIdJNDI(afiliadotipoidentificador.getAfiliadoId());
								existeBeneficiario = false;
								for (AfiliadoTipoIdentificador afiliadoTipoIdentificador2 : afiliadoTipoIdentificadors2) {
									if (afiliadoTipoIdentificador2.getTipoIdentificadorId() == TipoIdentificadorEnum.NUMBENEFICIARIOISSSTE.getId()) {
										existeBeneficiario = true;
										break;
									}
								}
									
								if (!existeBeneficiario) {
									afiliado = new Afiliado();
									afiliado.setAfiliadoId(afiliadotipoidentificador.getAfiliadoId());
									break;
								}
							}
						}else{
							Collection<AfiliadoTipoIdentificador> afiliadotipoidentificadors = cargaAgendaDao.getAfiliadoTipoIdentificadorIsssteJNDI(Integer.parseInt(cargaAgendaVo.getAfiliado()));
							for (AfiliadoTipoIdentificador afiliadotipoidentificador : afiliadotipoidentificadors) {
								Collection<AfiliadoTipoIdentificador> afiliadoTipoIdentificadors2 = cargaAgendaDao.getAfiliadoTipoIdentificadorIdJNDI(afiliadotipoidentificador.getAfiliadoId());
								for (AfiliadoTipoIdentificador afiliadoTipoIdentificador2 : afiliadoTipoIdentificadors2) {
									if (afiliadoTipoIdentificador2.getTipoIdentificadorId() == TipoIdentificadorEnum.NUMBENEFICIARIOISSSTE.getId()) {
										if(cargaAgendaVo.getBeneficiario().equals(afiliadoTipoIdentificador2.getTipoIdValor())){
											afiliado = new Afiliado();
											afiliado.setAfiliadoId(afiliadoTipoIdentificador2.getAfiliadoId());
											break;	
										}
										
									}
								}
								if (afiliado!=null) {
									break;
								}
							}
						}
						
						agenda = new Agenda();
						agenda.setAfiliado(afiliado);
						agenda.setAseguradores(aseguradores);
						agenda.setAsistio(0);
						agenda.setCatagendadopor(catagendadopor);
						agenda.setCattipoidentificador(cargaAgendaDao.getCatTipoIdentificadorByIdJNDI(Integer.parseInt(cargaAgendaVo.getTipoIden())));
						agenda.setClaveCita(cargaAgendaVo.getClaveCita());
						agenda.setConsultorio(cargaAgendaVo.getConsultorio());
						agenda.setConsultorioOriginal(cargaAgendaVo.getConsultorioOriginal());
						agenda.setFechaCita(FormatUtil.getDateTxt(cargaAgendaVo.getFecha()));
						agenda.setHoraCita(FormatUtil.getTime(cargaAgendaVo.getHora()));
						agenda.setLugaresdeatencion(cargaAgendaDao.getLugarAtencionByClaveJNDI(cargaAgendaVo.getClaveInst()));
						if (agenda.getConsultorio().indexOf("D")!=-1) {
							agenda.setPrestacion("4010001");
						}else{
							agenda.setPrestacion("0101001");	
						}
						agenda.setPrestadores(cargaAgendaDao.getPrestadoresByRfcJNDI(cargaAgendaVo.getPrestador()));
						Usuarios usuarios = cargaAgendaDao.getUsuarioByIdentificadorJNDI(TipoIdentificadorEnum.CVEMEDICO.getId(), cargaAgendaVo.getCveMedico());
						if (usuarios!=null) {
							agenda.setUsuarios(usuarios);
							if (agenda.getAfiliado()!=null) {
								if(cargaAgendaDao.existeAgendaJNDI(agenda)==null){
									if (cargaAgendaDao.existeAgendaByConsultorioJNDI(agenda)==null) {
										if (cargaAgendaDao.existeAgendaByUsuarioJNDI(agenda)==null) {
											cargaAgendaDao.saveAgendaJNDI(agenda);
											writeLog(directoryOut, "Se agrego exitosamente la agenda con los datos: asegurador = " + agenda.getAseguradores().getAseguradorId() + " prestador = " + agenda.getPrestadores().getPrestadorId() + " lugarDeAtencion = " + agenda.getLugaresdeatencion().getLugarDeAtencionId() + " consultorio ="+ agenda.getConsultorio() + " usuario = "+ agenda.getUsuarios().getUsuarioId());
										}else{
											//log.info("usuario duplicado");
											writeLog(directoryOut, "Error por usuario duplicado al agregar la agenda con los datos: asegurador = " + agenda.getAseguradores().getAseguradorId() + " prestador = " + agenda.getPrestadores().getPrestadorId() + " lugarDeAtencion = " + agenda.getLugaresdeatencion().getLugarDeAtencionId() + " consultorio ="+ agenda.getConsultorio() + " usuario = "+ agenda.getUsuarios().getUsuarioId());
										}	
									}else{
										//log.info("consultorio duplicado");
										writeLog(directoryOut, "Error por consultorio duplicado al agregar la agenda con los datos: asegurador = " + agenda.getAseguradores().getAseguradorId() + " prestador = " + agenda.getPrestadores().getPrestadorId() + " lugarDeAtencion = " + agenda.getLugaresdeatencion().getLugarDeAtencionId() + " consultorio ="+ agenda.getConsultorio() + " usuario = "+ agenda.getUsuarios().getUsuarioId()); 
									}
								}else{
									//log.info("agenda duplicada");
									writeLog(directoryOut, "Error por Agenda duplicado al agregar la agenda con los datos: asegurador = " + agenda.getAseguradores().getAseguradorId() + " prestador = " + agenda.getPrestadores().getPrestadorId() + " lugarDeAtencion = " + agenda.getLugaresdeatencion().getLugarDeAtencionId() + " consultorio ="+ agenda.getConsultorio() + " usuario = "+ agenda.getUsuarios().getUsuarioId()); 
								}	
							}else{
								//log.info("afiliado no encontrado");
								writeLog(directoryOut, "Error por afiliado no encontrado al agregar la agenda con los datos: asegurador = " + agenda.getAseguradores().getAseguradorId() + " prestador = " + agenda.getPrestadores().getPrestadorId() + " lugarDeAtencion = " + agenda.getLugaresdeatencion().getLugarDeAtencionId() + " consultorio ="+ agenda.getConsultorio() + " usuario = "+ agenda.getUsuarios().getUsuarioId() + " afiliado =" + cargaAgendaVo.getAfiliado()); 
							}
						}else{
							//log.info("usuario no encontrado");
							writeLog(directoryOut, "Error por usuario no encontrado al agregar la agenda con los datos: asegurador = " + agenda.getAseguradores().getAseguradorId() + " prestador = " + agenda.getPrestadores().getPrestadorId() + " lugarDeAtencion = " + agenda.getLugaresdeatencion().getLugarDeAtencionId() + " consultorio ="+ agenda.getConsultorio() + " usuario = "+ cargaAgendaVo.getCveMedico() + " afiliado =" + cargaAgendaVo.getAfiliado());														
						}
					}
	        	}catch(Exception e){
	        		writeLog(directoryOut, "Error al agregar la agenda con los datos: asegurador = " + agenda.getAseguradores().getAseguradorId() + " prestador = " + agenda.getPrestadores().getPrestadorId() + " lugarDeAtencion = " + agenda.getLugaresdeatencion().getLugarDeAtencionId() + " consultorio ="+ agenda.getConsultorio());
	        		e.printStackTrace();
	        	}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			writeLog(directoryOut, "Error al no encontrar el archivo de Citas");			
		} catch (IOException e) {
			e.printStackTrace();
			writeLog(directoryOut, "Error con la lectura del archivo de Citas");
		} catch (Exception e) {
			e.printStackTrace();
			writeLog(directoryOut, "Error en la carga de agenda");
		}
	}

	private void creartxtAgenda(String directoryOut, String carpeta) {
		try{
			String eol = System.getProperty("line.separator");
			writeLog(directoryOut, "Metodo creartxtAgenda");
			File myFile = new File(directoryOut+"Adicional-"+carpeta+".xlsx");
			FileInputStream fis = new FileInputStream(myFile);
			XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);
			XSSFSheet mySheet = myWorkBook.getSheetAt(0);
			Iterator<Row> rowIterator = mySheet.iterator();
			int i = 0;
			String contenido = "";
			int celda = 0;
			File file = new File(directoryOut+"Citas.txt");
			FileOutputStream fop = new FileOutputStream(file);

			if (!file.exists()) {
				file.createNewFile();
			}
			
			String claveInstitucional = "";
			String cveMedico = "";
			String consultorio = "";
			String fecha = "";
			String hora = "";
			String estatus = "";
			String afiliado = "";
			String beneficiario = "";
			String claveCita = "";
			String consultorioOriginal = "";
			
			while (rowIterator.hasNext()) {
			    Row row = rowIterator.next();
			    if (i!=0) {
			    	celda = 0;
					claveInstitucional = "";
					cveMedico = "";
					consultorio = "";
					fecha = "";
					hora = "";
					estatus = "";
					afiliado = "";
					beneficiario = "";
					claveCita = "";
					consultorioOriginal = "";			    	
			    	for(short colIndex = 0; colIndex < row.getLastCellNum(); colIndex++) {
					    Cell cell = row.getCell(colIndex);
					    if (celda==0) {
					    	if (cell!=null) {
						    	cell.setCellType(Cell.CELL_TYPE_STRING);
						    	cveMedico = cell.getStringCellValue();
					    	}
						}else if (celda==2) {
							if (cell!=null) {
								cell.setCellType(Cell.CELL_TYPE_STRING);
								consultorio = cell.getStringCellValue();
							}
						}else if (celda==4) {
							if (cell!=null) {
								fecha = FormatUtil.getDateTxt(cell.getDateCellValue());
							}
						}else if (celda==5) {
							if (cell!=null) {
								hora = FormatUtil.getTime(cell.getDateCellValue())+":00";
							}
						}else if (celda==7) {
							if (cell!=null) {
								cell.setCellType(Cell.CELL_TYPE_STRING);
								claveInstitucional = cell.getStringCellValue();
								estatus = ""+cell.getStringCellValue();
							}
						}else if (celda==10) {
							if (cell!=null) {
								cell.setCellType(Cell.CELL_TYPE_STRING);
								afiliado = ""+cell.getStringCellValue();
							}
						}else if (celda==11) {
							if (cell!=null) {
								cell.setCellType(Cell.CELL_TYPE_STRING);
								beneficiario = cell.getStringCellValue();	
							}else{
								beneficiario = "";
							}
						}else if (celda==13) {
							if (cell!=null) {
								cell.setCellType(Cell.CELL_TYPE_STRING);
								claveCita = cell.getStringCellValue();
							}
						}else if (celda==14) {
							if (cell!=null) {
								cell.setCellType(Cell.CELL_TYPE_STRING);
								consultorioOriginal = cell.getStringCellValue();
							}
						}
					    celda++;
				    }
			    	contenido = contenido + "ISSTE56782|"+claveInstitucional+"|"+cveMedico+"|"+consultorio+"|"+consultorioOriginal+"|"+estatus+"|"+claveCita+"|ISSTE56782|4|"+afiliado+"|1|"+fecha+"|"+hora+"|1|"+beneficiario+"|||"+eol;
				}
			    i++;
			}
			byte[] contentInBytes = contenido.getBytes();
			writeLog(directoryOut, "Se procede a ecribir en el archivo de Citas"); 
			fop.write(contentInBytes);
			fop.flush();
			fop.close();
			writeLog(directoryOut, "Archivo de Citas creado correctamente");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			writeLog(directoryOut, "Error al no encontrar el archivo de Excel");
		} catch (IOException e) {
			e.printStackTrace();
			writeLog(directoryOut, "Error con la lectura del archivo del Excel");
		}catch (Exception e) {
			e.printStackTrace();
			writeLog(directoryOut, "Error en la creacion del archivo de Citas");
		}
	}

	private void copyAllFiles(ChannelSftp sftp, String directoryIn, String directoryOut, String carpeta) throws IOException {
	    InputStream is = null;
	    OutputStream os = null;
	    Collection<String> archivos = new ArrayList<>();
	    archivos.add("issste-pacientes-"+carpeta+".txt");
	    archivos.add("Adicional-"+carpeta+".xlsx");
	    try {
	    	boolean success = (new File(directoryOut)).mkdirs();
	    	for (String archivo : archivos) {
	    		is = sftp.get(directoryIn+archivo);
	            os = new FileOutputStream(directoryOut+archivo);
		        byte[] buffer = new byte[1024];
		        int length;
		        while ((length = is.read(buffer)) > 0) {
		            os.write(buffer, 0, length);
		        }
	        }
	    } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SftpException e) {
			e.printStackTrace();
		} finally {
	        is.close();
	        os.close();
	    }
		
	}

	private void afiliados(ApplicationContext appCtx, IAgendaDao agendaDao, IGenericDao genericDao, File file, int idAsegurador, String directoryOut) {
		try{
			IAseguradorDao aseguradorDao = appCtx.getBean(IAseguradorDao.class);
			ICargaAgendaDao cargaAgendaDao = appCtx.getBean(ICargaAgendaDao.class);
			Catestados catestados = genericDao.getEstadoById(CatEstadosEnum.DISTRITO_FEDERAL.getId());
			Catsexos catsexosMasculino = genericDao.getCatSexos(CatSexoEnum.MASCULINO.getId());
			Catsexos catsexosFemenino = genericDao.getCatSexos(CatSexoEnum.FEMENINO.getId());
			Cattipoidentificador cattipoidentificadorIssste = genericDao.getCatTipoIdentificadorById(TipoIdentificadorEnum.NUMBENEFICIARIOISSSTE.getId());
			Cattipoidentificador cattipoidentificadorCurp = genericDao.getCatTipoIdentificadorById(TipoIdentificadorEnum.CURP.getId());
			Cattipoidentificador cattipoidentificadorRfc = genericDao.getCatTipoIdentificadorById(TipoIdentificadorEnum.RFC.getId());
			Cattipoidentificador cattipoidentificadorNumIssste = genericDao.getCatTipoIdentificadorById(TipoIdentificadorEnum.NUMISSSTE.getId());
			Aseguradores aseguradores = aseguradorDao.getAseguradorById(idAsegurador);
			writeLog(directoryOut, "Metodo afiliados");
			
			FileInputStream fis = null;
			
			fis = new FileInputStream(file);

	        BufferedReader br = null;
	        
	    	int posicionNumeroIssste = 5;
	    	int posicionBeneficiario = 6;
	    	Collection<CargaAfiliadosVo> cargaAfiliadosVos = new ArrayList<>();
	        br = new BufferedReader(new InputStreamReader(fis));
	    	String line;
	        while ((line = br.readLine()) != null) {
	        	String[] datos = line.split("\\|"); 
				CargaAfiliadosVo cargaAfiliadosVo = null;
				for(int i=0; i<datos.length; i++){
					if(i == posicionNumeroIssste){
						cargaAfiliadosVo = new CargaAfiliadosVo();
						cargaAfiliadosVo.setLinea(line);
						cargaAfiliadosVo.setNumeroIssste(datos[i]);
					}else if(i == posicionBeneficiario){
						cargaAfiliadosVo.setNumeroBeneficiario(datos[i]);
					}
				}
				cargaAfiliadosVos.add(cargaAfiliadosVo);
	        }	
	         
	        
	        boolean existeNumBeneficiario = false;
	        boolean agregarAfiliadoNuevo = true;
	        Collection<CargaAfiliadosVo> cargaAfiliadosVosNuevos = new ArrayList<>();
	        int i = 0;
	        for (CargaAfiliadosVo cargaAfiliadosVo : cargaAfiliadosVos) {
	        	agregarAfiliadoNuevo = true;
	        	System.out.println(i);
//		        	Collection<Afiliadotipoidentificador> afiliadotipoidentificadors = agendaDao.getAfiliadoTipoIdentificadorIssste(Integer.parseInt(cargaAfiliadosVo.getNumeroIssste()));
	        	Collection<AfiliadoTipoIdentificador> afiliadotipoidentificadors = cargaAgendaDao.getAfiliadoTipoIdentificadorIsssteJNDI(Integer.parseInt(cargaAfiliadosVo.getNumeroIssste()));
	        	i++;
	        	if (!afiliadotipoidentificadors.isEmpty()) {
	        		if (cargaAfiliadosVo.getNumeroBeneficiario().equals("0")) {
	        			Collection<AfiliadoTipoIdentificador> afiliadotipoidentificadorSoloIssste = new ArrayList<>();
	        			for (AfiliadoTipoIdentificador afiliadotipoidentificador : afiliadotipoidentificadors) {
							Collection<AfiliadoTipoIdentificador> afiliadoTipoIdentificadorIterator = cargaAgendaDao.getAfiliadoTipoIdentificadorIdJNDI(afiliadotipoidentificador.getAfiliadoId());
							existeNumBeneficiario = false;
							for (AfiliadoTipoIdentificador afiliadotipoidentificador2 : afiliadoTipoIdentificadorIterator) {
								if (afiliadotipoidentificador2.getTipoIdentificadorId() == TipoIdentificadorEnum.NUMBENEFICIARIOISSSTE.getId()) {
									existeNumBeneficiario = true;
									break;
								}
							}
							if (!existeNumBeneficiario) {
								afiliadotipoidentificadorSoloIssste.add(afiliadotipoidentificador);
							}
						}
	        			
	        			if (afiliadotipoidentificadorSoloIssste.size()>0) {
							agregarAfiliadoNuevo = false;
						}else{
							agregarAfiliadoNuevo = true;
						}		
					}else{
						for (AfiliadoTipoIdentificador afiliadotipoidentificador : afiliadotipoidentificadors) {
							Collection<AfiliadoTipoIdentificador> afiliadoTipoIdentificadorIterator = cargaAgendaDao.getAfiliadoTipoIdentificadorIdJNDI(afiliadotipoidentificador.getAfiliadoId());
							for (AfiliadoTipoIdentificador afiliadotipoidentificador2 : afiliadoTipoIdentificadorIterator) {
								if (afiliadotipoidentificador2.getTipoIdentificadorId() == TipoIdentificadorEnum.NUMBENEFICIARIOISSSTE.getId()) {
									if(afiliadotipoidentificador2.getTipoIdValor().equals(cargaAfiliadosVo.getNumeroBeneficiario())){
										agregarAfiliadoNuevo = false;
										break;
									}
								}
							}
						}		
					}
	        		if (agregarAfiliadoNuevo) {
	        			cargaAfiliadosVosNuevos.add(cargaAfiliadosVo);	
					}
	        		
				}else {
					cargaAfiliadosVosNuevos.add(cargaAfiliadosVo);
				}
			}
	        
	        for (CargaAfiliadosVo cargaAfiliadosVo : cargaAfiliadosVosNuevos) {
	        	try{
		        	insertarAfiliado(cargaAfiliadosVo);	
		        	Afiliado afiliado = new Afiliado();
		        	afiliado.setActivo(1);
		        	afiliado.setApellidoMaterno(cargaAfiliadosVo.getApellidoMaterno());
		        	afiliado.setApellidoPaterno(cargaAfiliadosVo.getApellidoPaterno());
		        	afiliado.setCatestadosByEstadoDeNacimientoId(catestados);
		        	afiliado.setCatestadosByEstadoId(catestados);
		        	if(cargaAfiliadosVo.getSexo().equals("M")){
		        		afiliado.setCatsexos(catsexosMasculino);
		        	}else{
		        		afiliado.setCatsexos(catsexosFemenino);	
		        	}
		        	
		        	afiliado.setFechaAlta(new Date());
		        	afiliado.setNombre(cargaAfiliadosVo.getNombre());
		        	int derechohabiente = 0;
		            if(cargaAfiliadosVo.getDerechohabiente().equals("T")){
		                if(afiliado.getCatsexos().getSexoId() == CatSexoEnum.MASCULINO.getId()){
		                    derechohabiente = 10;
		                }else{
		                    derechohabiente = 20;
		                }
		            }else if(cargaAfiliadosVo.getDerechohabiente().equals("P")){
		                if(afiliado.getCatsexos().getSexoId() == CatSexoEnum.MASCULINO.getId()){
		                    derechohabiente = 90;
		                }else{
		                    derechohabiente = 91;
		                }
		            }else if(cargaAfiliadosVo.getDerechohabiente().equals("TP")){
		                derechohabiente = 10;
		            }	
		            
		            Cattipoafiliado cattipoafiliado = cargaAgendaDao.getCatTipoAfiliadoByClaveJNDI(derechohabiente, idAsegurador);
		        	afiliado.setCattipoafiliado(cattipoafiliado);
		        	
		        	FormatUtil.getFechaFromCurp(cargaAfiliadosVo);	
		        	afiliado.setFechaDeNacimiento(FormatUtil.getDate(cargaAfiliadosVo.getFechaNacimiento()));
		        	afiliado = cargaAgendaDao.saveAfiliadoJNDI(afiliado);
		        	writeLog(directoryOut, "Se guardo el afiliado con los datos: nombre = " + afiliado.getNombre() + " apellidoPaterno = " + afiliado.getApellidoPaterno() + " apellidoMaterno =" + afiliado.getApellidoMaterno());

		        	AfiliadoAsegurador afiliadoAsegurador = new AfiliadoAsegurador();
		        	afiliadoAsegurador.setAfiliado(afiliado);
		        	
		        	afiliadoAsegurador.setAseguradores(aseguradores);
		        	AfiliadoAseguradorId afiliadoAseguradorId = new AfiliadoAseguradorId();
		        	afiliadoAseguradorId.setAfiliadoId(afiliado.getAfiliadoId());
		        	afiliadoAseguradorId.setAseguradorId(aseguradores.getAseguradorId());
		        	afiliadoAsegurador.setId(afiliadoAseguradorId);
		        	cargaAgendaDao.saveAfiliadoAseguradorJNDI(afiliadoAsegurador);
		        	
		        	Afiliadotipoidentificador afiliadotipoidentificador = new Afiliadotipoidentificador();
		        	afiliadotipoidentificador.setAfiliado(afiliado);
		        	afiliadotipoidentificador.setCattipoidentificador(cattipoidentificadorNumIssste);
		        	afiliadotipoidentificador.setTipoIdValor(cargaAfiliadosVo.getNumeroIssste());
		        	AfiliadotipoidentificadorId afiliadotipoidentificadorId = new AfiliadotipoidentificadorId();
		        	afiliadotipoidentificadorId.setAfiliadoId(afiliado.getAfiliadoId());
		        	afiliadotipoidentificadorId.setTipoIdentificadorId(TipoIdentificadorEnum.NUMISSSTE.getId());
		        	afiliadotipoidentificador.setId(afiliadotipoidentificadorId);
		        	cargaAgendaDao.saveAfiliadotipoIdentificarJNDI(afiliadotipoidentificador);
		        	
		        	afiliadotipoidentificador = new Afiliadotipoidentificador();
		        	afiliadotipoidentificador.setAfiliado(afiliado);
		        	afiliadotipoidentificador.setCattipoidentificador(cattipoidentificadorRfc);
		        	afiliadotipoidentificador.setTipoIdValor(cargaAfiliadosVo.getRfc());
		        	afiliadotipoidentificadorId = new AfiliadotipoidentificadorId();
		        	afiliadotipoidentificadorId.setAfiliadoId(afiliado.getAfiliadoId());
		        	afiliadotipoidentificadorId.setTipoIdentificadorId(TipoIdentificadorEnum.RFC.getId());
		        	afiliadotipoidentificador.setId(afiliadotipoidentificadorId);
		        	cargaAgendaDao.saveAfiliadotipoIdentificarJNDI(afiliadotipoidentificador);
		        	
		        	afiliadotipoidentificador = new Afiliadotipoidentificador();
		        	afiliadotipoidentificador.setAfiliado(afiliado);
		        	afiliadotipoidentificador.setCattipoidentificador(cattipoidentificadorCurp);
		        	afiliadotipoidentificador.setTipoIdValor(cargaAfiliadosVo.getCurp());
		        	afiliadotipoidentificadorId = new AfiliadotipoidentificadorId();
		        	afiliadotipoidentificadorId.setAfiliadoId(afiliado.getAfiliadoId());
		        	afiliadotipoidentificadorId.setTipoIdentificadorId(TipoIdentificadorEnum.CURP.getId());
		        	afiliadotipoidentificador.setId(afiliadotipoidentificadorId);
		        	cargaAgendaDao.saveAfiliadotipoIdentificarJNDI(afiliadotipoidentificador);	
		        	
		        	if (!cargaAfiliadosVo.getNumeroBeneficiario().equals("0")) {
		        		afiliadotipoidentificador = new Afiliadotipoidentificador();
			        	afiliadotipoidentificador.setAfiliado(afiliado);
			        	afiliadotipoidentificador.setCattipoidentificador(cattipoidentificadorIssste);
			        	afiliadotipoidentificador.setTipoIdValor(cargaAfiliadosVo.getNumeroBeneficiario());
			        	afiliadotipoidentificadorId = new AfiliadotipoidentificadorId();
			        	afiliadotipoidentificadorId.setAfiliadoId(afiliado.getAfiliadoId());
			        	afiliadotipoidentificadorId.setTipoIdentificadorId(TipoIdentificadorEnum.NUMBENEFICIARIOISSSTE.getId());
			        	afiliadotipoidentificador.setId(afiliadotipoidentificadorId);
			        	cargaAgendaDao.saveAfiliadotipoIdentificarJNDI(afiliadotipoidentificador);	
					}
	        	}catch(Exception ex){
	        		ex.printStackTrace(); 
	        		writeLog(directoryOut, "Error al guardar el afiliado con los datos: nombre = " + cargaAfiliadosVo.getNombre() + " apellidoPaterno = " + cargaAfiliadosVo.getApellidoPaterno() + " apellidoMaterno =" + cargaAfiliadosVo.getApellidoMaterno());
	        	}
			}

		}catch(IOException e){
			e.printStackTrace();
			writeLog(directoryOut, "Error al procesar el archivo de afiliados");
		}catch (Exception e) {
			e.printStackTrace();
			writeLog(directoryOut, "Error en la carga de afiliados");
		}
	}

	private void insertarAfiliado(CargaAfiliadosVo cargaAfiliadosVo) {
		String[] datos = cargaAfiliadosVo.getLinea().split("\\|");
		int posicionRfc = 3;
		int posicionCurp = 4;
		int posicionDerechohabiente = 7;
		int posicionNombre = 8;
		int posicionPaterno = 9;
		int posicionMaterno = 10;
		int posicionSexo = 13;
		int posicionFecha = 14;
		
		for(int i=0; i<datos.length; i++){
			if(i == posicionRfc){
				cargaAfiliadosVo.setRfc(datos[i]);
			}else if(i == posicionCurp){
				cargaAfiliadosVo.setCurp(datos[i]);
			}else if(i == posicionDerechohabiente){
				cargaAfiliadosVo.setDerechohabiente(datos[i]);
			}else if(i == posicionNombre){
				cargaAfiliadosVo.setNombre(datos[i]);
			}else if(i == posicionPaterno){
				cargaAfiliadosVo.setApellidoPaterno(datos[i]);
			}else if(i == posicionMaterno){
				cargaAfiliadosVo.setApellidoMaterno(datos[i]);
			}else if(i == posicionSexo){
				cargaAfiliadosVo.setSexo(datos[i]);
			}else if(i == posicionFecha){
				cargaAfiliadosVo.setFecha(datos[i]);
			}
		}
	}
	
	private void writeLog(String carpeta, String text){
		BufferedWriter out = null;
		try  {
			String eol = System.getProperty("line.separator");
		    FileWriter fstream = new FileWriter(carpeta + "Log.txt", true); //true tells to append data.
		    out = new BufferedWriter(fstream);
		    out.write(text+eol);
		}catch (IOException e){
		    System.err.println("Error: " + e.getMessage());
		}finally{
		    if(out != null) {
		        try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		}
	}



}
